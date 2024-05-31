package ai.opensource.emago.viewmodels

import ai.opensource.emago.data.ChatData
import ai.opensource.emago.data.ChatRepository
import ai.opensource.emago.data.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {
    val chats = MutableStateFlow<List<ChatData>>(emptyList())
    val chatMessages = MutableStateFlow<List<Message>>(emptyList())
    val inProcess = MutableStateFlow(false)
    val errorMessage = MutableStateFlow<String?>(null)

    fun getAllChatData() {
        viewModelScope.launch {
            val result = repository.getAllChatData()
            if (result.isSuccess) {
                chats.value = result.getOrDefault(emptyList())
            } else {
                errorMessage.value = result.exceptionOrNull()?.message
            }
        }
    }

    fun addChat(title: String, description: String) {
        if (title.isEmpty()) {
            errorMessage.value = "Title must be provided!"
            return
        }

        inProcess.value = true
        viewModelScope.launch {
            val result = repository.addChat(title, description)
            inProcess.value = false
            if (result.isSuccess) {
                getAllChatData()
            } else {
                errorMessage.value = result.exceptionOrNull()?.message
            }
        }
    }

    fun sendReply(chatId: String, message: String) {
        inProcess.value = true
        viewModelScope.launch {
            val result = repository.sendReply(chatId, message)
            inProcess.value = false
            if (result.isFailure) {
                errorMessage.value = result.exceptionOrNull()?.message
            }
        }
    }

    fun populateMessages(chatId: String) {
        inProcess.value = true
        repository.populateMessages(chatId, { messages ->
            chatMessages.value = messages
            inProcess.value = false
        }, { error ->
            errorMessage.value = error.message
            inProcess.value = false
        })
    }

    fun depopulateMessages() {
        repository.depopulateMessages()
        chatMessages.value = emptyList()
    }
}
