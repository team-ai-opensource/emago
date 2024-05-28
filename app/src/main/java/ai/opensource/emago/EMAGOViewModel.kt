package ai.opensource.emago

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import ai.opensource.emago.data.Event
import ai.opensource.emago.data.USER_NODE
import ai.opensource.emago.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EMAGOViewModel @Inject constructor(
    val auth : FirebaseAuth,
    var db: FirebaseFirestore,
) : ViewModel() {

    var inProcess = mutableStateOf(false)
    val eventMutableState = mutableStateOf<Event<String?>>(Event(null))
    var signIn = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)

    init {
        val currentUser = auth.currentUser
        signIn.value = currentUser != null
        currentUser?.uid?.let {
            getUserData(it)
        }
    }


    fun signUp(name: String, number: String, email: String, password: String){
        inProcess.value = true

        if (name.isEmpty() or number.isEmpty() or email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = " Please Fill All fields ")
            return
        }

        inProcess.value = true

        db.collection(USER_NODE).whereEqualTo("number", number).get().addOnSuccessListener {

            if ( it.isEmpty) {
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
                handleException(customMessage = " number Already exists")
                inProcess.value = false
            }
        }



    }

    fun createOrUpdateProfile(name: String?=null, number: String?=null, imageurl: String?=null) {
        var uid=auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name= name?: userData.value?.name, // 이름이 없을 때는 기존이름으로 같은 느낌?
            number= number?: userData.value?.number,
            imageUrl= imageurl?: userData.value?.imageUrl
        )

        uid?.let {
            inProcess.value = true
            db.collection(USER_NODE).document(uid).get().addOnCompleteListener {

                if (it.result.exists()) {
                    // update user Data
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
        db.collection(USER_NODE).document(uid).addSnapshotListener{
                value, error ->
            if (error!=null) {
                handleException(error, " Cannot Retrieve User")
            }

            if (value != null) {
                var user = value.toObject<UserData>()
                userData.value = user
                inProcess.value = false
            }
        }
    }

    fun handleException(exception: Exception?=null, customMessage: String="") {
        Log.d("LiveChatApp", "live chat exception: ", exception)
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage?: ""
        val message = if(customMessage.isNullOrEmpty()) errorMsg else customMessage

        eventMutableState.value = Event(message)
        inProcess.value = false
    }
}

