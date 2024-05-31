package ai.opensource.emago.data

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.*

class DBRepository(
    val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage
) {
    suspend fun createOrUpdateProfile(name: String? = null, number: String? = null, imageurl: String? = null): Result<Boolean> {
        val uid = auth.currentUser?.uid ?: return Result.failure(Exception("User not logged in"))

        val userData = UserData(
            userId = uid,
            name = name ?: getUserData(uid)?.name,
            number = number ?: getUserData(uid)?.number,
            profileImageUrl = imageurl ?: getUserData(uid)?.profileImageUrl
        )

        return try {
            val userDocument = db.collection(AuthRepository.USER_NODE).document(uid).get().await()
            if (userDocument.exists()) {
                val updates = hashMapOf<String, Any?>()
                if (name != null) updates["name"] = name
                if (number != null) updates["number"] = number
                if (imageurl != null) updates["imageUrl"] = imageurl

                db.collection(AuthRepository.USER_NODE).document(uid).update(updates).await()
            } else {
                db.collection(AuthRepository.USER_NODE).document(uid).set(userData).await()
            }
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun getUserData(uid: String): UserData? {
        return try {
            val document = db.collection(AuthRepository.USER_NODE).document(uid).get().await()
            document.toObject<UserData>()
        } catch (e: Exception) {
            null
        }
    }

    fun getUserData(uid: String, onComplete: (UserData?) -> Unit) {
        db.collection(AuthRepository.USER_NODE).document(uid).addSnapshotListener { value, error ->
            if (error != null) {
                onComplete(null)
            } else {
                val userData = value?.toObject<UserData>()
                onComplete(userData)
            }
        }
    }

    fun uploadImage(uri: Uri, onSuccess: (Uri) -> Unit, onFailure: (Exception) -> Unit) {
        val storageRef = storage.reference
        val uuid = UUID.randomUUID()
        val imageRef = storageRef.child("images/$uuid")
        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnSuccessListener {
            val result = it.metadata?.reference?.downloadUrl
            result?.addOnSuccessListener(onSuccess)
        }.addOnFailureListener(onFailure)
    }
}
