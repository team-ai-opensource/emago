package ai.opensource.emago.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {
    suspend fun signUp(name: String, number: String, email: String, password: String): Result<Boolean> {
        if (name.isEmpty() || number.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return Result.failure(Exception("Please Fill All fields"))
        }

        return try {
            val existingUsers = db.collection(USER_NODE).whereEqualTo("number", number).get().await()
            if (existingUsers.isEmpty) {
                auth.createUserWithEmailAndPassword(email, password).await()
                Result.success(true)
            } else {
                Result.failure(Exception("User Already Exists"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<Boolean> {
        return try {
            if (email.isEmpty() || password.isEmpty()) {
                return Result.failure(Exception("Please Fill All fields"))
            }

            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    companion object {
        const val USER_NODE = "users"
    }
}
