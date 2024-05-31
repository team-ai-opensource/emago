package ai.opensource.emago.viewmodel

import ai.opensource.emago.data.DBRepository
import ai.opensource.emago.data.UserData
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DBViewModel @Inject constructor(
    private val repository: DBRepository
) : ViewModel() {
    val userData = MutableStateFlow<UserData?>(null)
    val inProcess = MutableStateFlow(false)
    val errorMessage = MutableStateFlow<String?>(null)

    fun createOrUpdateProfile(name: String? = null, number: String? = null, imageurl: String? = null) {
        inProcess.value = true
        viewModelScope.launch {
            val result = repository.createOrUpdateProfile(name, number, imageurl)
            inProcess.value = false
            if (result.isFailure) {
                errorMessage.value = result.exceptionOrNull()?.message
            } else {
                getUserData()
            }
        }
    }

    fun getUserData() {
        val uid = repository.auth.currentUser?.uid ?: return
        repository.getUserData(uid) { user ->
            userData.value = user
            inProcess.value = false
        }
    }

    fun uploadProfileImage(uri: Uri) {
        inProcess.value = true
        repository.uploadImage(uri, { imageUrl ->
            createOrUpdateProfile(imageurl = imageUrl.toString())
            inProcess.value = false
        }, { exception ->
            errorMessage.value = exception.message
            inProcess.value = false
        })
    }
}
