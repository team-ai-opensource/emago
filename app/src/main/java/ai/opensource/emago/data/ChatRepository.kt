package ai.opensource.emago.data

import ai.opensource.emago.utils.sendPostRequest
import android.icu.util.Calendar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class ChatRepository(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {
    private var currentChatMessageListener: ListenerRegistration? = null

    suspend fun getAllChatData(): Result<List<ChatData>> {
        return try {
            val result = db.collection(CHATS).get().await()
            val chatList = result.documents.mapNotNull { document ->
                document.toObject<ChatData>()?.apply {
                    id = document.id // 문서 ID를 Chat 객체의 id 필드에 할당
                }
            }
            Result.success(chatList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addChat(title: String, description: String): Result<Boolean> {
        return try {
            val uid = auth.currentUser?.uid ?: return Result.failure(Exception("User not logged in"))
            db.collection(CHATS).document().set(ChatData(userId = uid, title = title, description = description)).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendReply(chatId: String, message: String, userData: UserData?): Result<Boolean> {
        return try {
            val time = Calendar.getInstance().time.toString()
            val chatUser = ChatUser(
                userId = auth.currentUser?.uid,
                name = "User Name", // You might want to fetch user details
                imageUrl = "User Image URL",
                number = "User Number"
            )
            val msg = Message(chatId, chatUser, time, message)

            val documentReference = db.collection(MESSAGES).add(msg).await()
            val documentId = documentReference.id

            val jsonBody = """{
                "messageId" : "$documentId",
                "message" : "$message",
                }"""
            val url = "http://huseong.iptime.org:8000/api/emago"

            withContext(Dispatchers.IO){
                val response = sendPostRequest(url, jsonBody)
                println(response)
            }
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun populateMessages(chatId: String, onUpdate: (List<Message>) -> Unit, onError: (Exception) -> Unit) {
        currentChatMessageListener = db.collection(MESSAGES).whereEqualTo("chatId", chatId)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onError(error)
                } else {
                    val messages = value?.documents?.mapNotNull { it.toObject<Message>() }?.sortedBy { it.timestamp }
                    messages?.let { onUpdate(it) }
                }
            }
    }

    fun depopulateMessages() {
        currentChatMessageListener?.remove()
        currentChatMessageListener = null
    }

    companion object {
        const val CHATS = "chats"
        const val MESSAGES = "messages"
    }
}
