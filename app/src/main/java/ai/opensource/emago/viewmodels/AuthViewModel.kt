package ai.opensource.emago.viewmodels

import ai.opensource.emago.data.AuthRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.appcheck.internal.util.Logger.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(repository.isLoggedIn())

    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn //비동기 로그인 상태

    private val _inProcess = MutableStateFlow(false) // 비동기 작업상태 확인 - 수정가능한 상태값
    val inProcess: StateFlow<Boolean> get() = _inProcess // 비동기 작업상태 확인 - _inProcess를 읽기전용으로 반환

    private val _errorMessage = MutableStateFlow<String?>(null)  // 비동기 에러메시지 저장 - 수정가능한 상태값
    val errorMessage: StateFlow<String?> get() = _errorMessage // 비동기 에러메시지 저장 - _errorMessage를 읽기전용으로 반환

    // 회원가입

    fun signUp(name: String, number: String, email: String, password: String) {
        _inProcess.value = true // 작업상태 true로 변경
        viewModelScope.launch {
            val result = repository.signUp(name, number, email, password)
            _inProcess.value = false
            if (result.isSuccess) {
                Log.d(TAG, "signUp: success")
                _isLoggedIn.value = true
            } else {
                _errorMessage.value = result.exceptionOrNull()?.message
            }
        }
    }

    fun login(email: String, password: String) {
        _inProcess.value = true
        viewModelScope.launch {
            val result = repository.login(email, password) //
            _inProcess.value = false
            if (result.isSuccess) {
                Log.d(TAG, "login: success")
                _isLoggedIn.value = true
            } else {
                _errorMessage.value = result.exceptionOrNull()?.message
            }
        }
    }

    fun logout() {
        repository.logout()
        _isLoggedIn.value = false
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
