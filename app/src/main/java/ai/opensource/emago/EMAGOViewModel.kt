package ai.opensource.emago

import ai.opensource.emago.data.CHATS
import ai.opensource.emago.data.ChatData
import ai.opensource.emago.data.ChatUser
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import ai.opensource.emago.data.Event
import ai.opensource.emago.data.MESSAGES
import ai.opensource.emago.data.Message
import ai.opensource.emago.data.USER_NODE
import ai.opensource.emago.data.UserData
import ai.opensource.emago.util.sendPostRequest
import android.icu.util.Calendar
import android.net.Uri
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class EMAGOViewModel @Inject constructor(
    val auth: FirebaseAuth,
    var db: FirebaseFirestore,
    val storage: FirebaseStorage,
) : ViewModel() {

    var inProcess = mutableStateOf(false)
    var inProcessChats = mutableStateOf(false)
    val eventMutableState = mutableStateOf<Event<String?>>(Event(null))
    var signIn = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val chats = mutableStateOf<List<ChatData>?>(listOf())

    val chatMessages = mutableStateOf<List<Message>?>(listOf())
    val inProgressChatMessage = mutableStateOf(false)
    var currentChatMessageListner: ListenerRegistration? = null

    init {
        val currentUser = auth.currentUser
        signIn.value = currentUser != null
        currentUser?.uid?.let {
            getUserData(it)
        }
    }

    fun populateMessages(chatId: String) {
        inProgressChatMessage.value = true
        currentChatMessageListner =
            db.collection(MESSAGES).whereEqualTo("chatId", chatId)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        handleException(error)
                    }
                    if (value != null) {
                        chatMessages.value = value.documents.mapNotNull {
                            it.toObject<Message>()
                        }.sortedBy { it.timestamp }
                        inProgressChatMessage.value = false
                    }
                }
    }

    fun depopulateMessage() {
        chatMessages.value = listOf()
        currentChatMessageListner = null
    }


    fun signUp(name: String, number: String, email: String, password: String) {
        inProcess.value = true

        if (name.isEmpty() or number.isEmpty() or email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = " Please Fill All fields ")
            return
        }

        inProcess.value = true

        db.collection(USER_NODE).whereEqualTo("number", number).get().addOnSuccessListener {

            if (it.isEmpty) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() {


                    if (it.isSuccessful) {
                        signIn.value = true
                        createOrUpdateProfile(name, number)
                        Log.d("TAG", "signUp: User Logged IN")
                    } else {
                        handleException(it.exception, customMessage = "SignUp: User Logged IN")
                    }
                }
            } else {
                handleException(customMessage = "user Already exists")
                inProcess.value = false
            }
        }


    }

    fun loginIn(email: String, password: String) {

        if (email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = " Please Fill the all Fields")
            return
        } else {
            inProcess.value = true
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        signIn.value = true
                        inProcess.value = false
                        auth.currentUser?.uid?.let {
                            getUserData(it)
                        }
                    } else {
                        inProcess.value = false
                    }
                }
        }
    }

    fun uploadProfileImage(uri: Uri) {
        uploadImage(uri) {
            createOrUpdateProfile(imageurl = it.toString())
        }


    }

    fun uploadImage(uri: Uri, onSuccess: (Uri) -> Unit) {
        inProcess.value = true

        val storageRef = storage.reference
        val uuid = UUID.randomUUID()
        val imageRef = storageRef.child("images/$uuid")
        val uploadTask = imageRef.putFile(uri)
        uploadTask.addOnSuccessListener {

            val result = it.metadata?.reference?.downloadUrl

            result?.addOnSuccessListener(onSuccess)
            inProcess.value = false


        }.addOnFailureListener {
            handleException(it)
        }

    }

    fun createOrUpdateProfile(
        name: String? = null,
        number: String? = null,
        imageurl: String? = null
    ) {
        var uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name ?: userData.value?.name, // 이름이 없을 때는 기존이름으로 같은 느낌?
            number = number ?: userData.value?.number,
            imageUrl = imageurl ?: userData.value?.imageUrl
        )

        uid?.let {
            inProcess.value = true
            db.collection(USER_NODE).document(uid).get().addOnCompleteListener {

                if (it.result.exists()) {
                    // Update user data
                    val updates = hashMapOf<String, Any?>()
                    if (name != null) updates["name"] = name
                    if (number != null) updates["number"] = number
                    if (imageurl != null) updates["imageUrl"] = imageurl

                    db.collection(USER_NODE).document(uid).update(updates)
                        .addOnSuccessListener {
                            inProcess.value = false
                            getUserData(uid)
                        }
                        .addOnFailureListener { e ->
                            handleException(e, "Cannot Update User")
                            inProcess.value = false
                        }
                } else {

                    db.collection(USER_NODE).document(uid).set(userData)
                    inProcess.value = false
                    getUserData(uid)
                }
            }
                .addOnFailureListener {
                    handleException(it, " Cannot Retrieve User")
                }
        }
    }

    private fun getUserData(uid: String) {
        db.collection(USER_NODE).document(uid).addSnapshotListener { value, error ->
            if (error != null) {
                handleException(error, " Cannot Retrieve User")
            }

            if (value != null) {
                var user = value.toObject<UserData>()
                userData.value = user
                inProcess.value = false
            }
        }
    }

    fun handleException(exception: Exception? = null, customMessage: String = "") {
        Log.d("LiveChatApp", "live chat exception: ", exception)
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isNullOrEmpty()) errorMsg else customMessage

        eventMutableState.value = Event(message)
        inProcess.value = false
    }

    fun logout() {
        auth.signOut()
        signIn.value = false
        userData.value = null
        eventMutableState.value = Event("Logged Out")
    }

    fun getAllChatData() {
        db.collection(CHATS).get()
            .addOnSuccessListener { result ->
                // 모든 문서를 Chat 객체로 변환하여 리스트로 만듭니다.
                val chatList = result.documents.mapNotNull { document ->
                    document.toObject<ChatData>()?.apply {
                        id = document.id // 문서 ID를 Chat 객체의 id 필드에 할당
                    }
                }
                // chats LiveData에 리스트를 할당합니다.
                chats.value = chatList
            }
            .addOnFailureListener { exception ->
                // 에러 처리
                handleException(exception)
            }
    }

    fun onAddChat(title: String, description: String) {
        if (title.isEmpty()) {
            handleException(customMessage = "Number must be contain digits only!")
        } else {
            var uid = auth.currentUser?.uid
            db.collection(CHATS).document()
                .set(ChatData(userId = uid, title = title, description = description))
                .addOnSuccessListener {

                    // 채팅방 목록 불러오기
                    getAllChatData()


                }
        }
    }

    fun onSendReply(chatId: String, message: String) {
        val time = Calendar.getInstance().time.toString()
        val chatUser: ChatUser = ChatUser(
            userData.value?.userId,
            userData.value?.name,
            userData.value?.imageUrl,
            userData.value?.number
        )
        val msg: Message = Message(chatId, chatUser, time, message)

        // Firestore에 문서를 추가하고 문서 ID를 가져옴
        db.collection(MESSAGES).add(msg).addOnSuccessListener { documentReference ->
            val documentId = documentReference.id

            // 문서 ID를 포함한 JSON 본문 생성
            val jsonBody = """{"messageId": "$documentId", "message": "$message"}"""


            Log.d("test", "onSendReply: $jsonBody")

            // POST 요청을 보낼 URL
            val url = "http://huseong.iptime.org:8000/api/emago"

            // 네트워크 요청을 비동기적으로 수행
            GlobalScope.launch(Dispatchers.IO) {
                val response = sendPostRequest(url, jsonBody)
                println(response)
            }
        }.addOnFailureListener { e ->
            e.printStackTrace()
        }
    }


}

