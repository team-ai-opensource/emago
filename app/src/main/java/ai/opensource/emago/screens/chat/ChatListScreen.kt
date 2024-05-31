package ai.opensource.emago.screens.chat

import ai.opensource.emago.data.ChatData
import ai.opensource.emago.utils.TitleText
import ai.opensource.emago.viewmodel.DBViewModel
import ai.opensource.emago.viewmodels.ChatViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ChatListScreen(navController: NavController, cvm: ChatViewModel = hiltViewModel(), dvm: DBViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) { cvm.getAllChatData() }
    val chats by cvm.chats.collectAsState()
    val userData by dvm.userData.collectAsState()

    // FAB Variables
    val showDialog = remember { mutableStateOf(false) }
    val onFABClick: () -> Unit = { showDialog.value = true }
    val onDismiss: () -> Unit = { showDialog.value = false }
    val onAddChat = fun(title: String, description: String) {
        cvm.addChat(title, description)
        showDialog.value = false
    }

    Scaffold(
        floatingActionButton = {
            FAB(
                showDialog = showDialog.value,
                onFabClick = onFABClick,
                onDismiss = onDismiss,
                onAddChat = onAddChat,
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                TitleText(text = "Chats")

                if (chats.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "채팅방이 존재하지 않습니다.")
                    }
                } else {
                    ChatListBox(navController, modifier = Modifier.fillMaxWidth(), chatList = chats)
                }
            }
        }
    )
}

@Composable
fun FAB(
    showDialog: Boolean,
    onFabClick: () -> Unit,
    onDismiss: () -> Unit,
    onAddChat: (String, String) -> Unit
) {
    val addChatTitle = remember { mutableStateOf("") }
    val addChatDescription = remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
                addChatTitle.value = ""
                addChatDescription.value = ""
            },
            confirmButton = {
                Button(onClick = { onAddChat(addChatTitle.value, addChatDescription.value) }) {
                    Text(text = "Add Chat")
                }
            },
            title = {
                Text(text = "Add Chat")
            },
            text = {
                Column {
                    Text("제목")
                    OutlinedTextField(
                        value = addChatTitle.value,
                        onValueChange = { addChatTitle.value = it },
                    )
                    Text("내용")
                    OutlinedTextField(
                        value = addChatDescription.value,
                        onValueChange = { addChatDescription.value = it },
                    )
                }
            }
        )
    }

    FloatingActionButton(
        onClick = { onFabClick() },
        containerColor = MaterialTheme.colorScheme.secondary,
        shape = CircleShape,
        modifier = Modifier.padding(bottom = 40.dp)
    ) {
        Icon(imageVector = Icons.Rounded.Add, contentDescription = null, tint = Color.White)
    }
}

@Composable
fun ChatListBox(navController: NavController, modifier: Modifier, chatList: List<ChatData>) {
    LazyColumn(modifier = modifier) {
        items(chatList) { item ->
            Column {
                Text(
                    text = item.title.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("chat/${item.id}")
                        }
                        .padding(16.dp)
                )
                Divider()
            }
        }
    }
}