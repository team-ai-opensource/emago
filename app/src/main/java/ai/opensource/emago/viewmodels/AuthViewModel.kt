package ai.opensource.emago.viewmodels

import ai.opensource.emago.data.AuthRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private val _inProcess = MutableStateFlow(false)
    val inProcess: StateFlow<Boolean> get() = _inProcess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun signUp(name: String, number: String, email: String, password: String) {
        _inProcess.value = true
        viewModelScope.launch {
            val result = repository.signUp(name, number, email, password)
            _inProcess.value = false
            if (result.isSuccess) {
                _isLoggedIn.value = true
            } else {
                _errorMessage.value = result.exceptionOrNull()?.message
            }
        }
    }

    fun login(email: String, password: String) {
        _inProcess.value = true
        viewModelScope.launch {
            val result = repository.login(email, password)
            _inProcess.value = false
            if (result.isSuccess) {
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
