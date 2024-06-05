package ai.opensource.emago.screens.chat


import ai.opensource.emago.EMAGOViewModel
import ai.opensource.emago.data.ChatData
import ai.opensource.emago.util.CommonProgressBar
import ai.opensource.emago.util.TitleText
import ai.opensource.emago.util.navigateTo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable

fun ChatListScreen(navController: NavController, vm: EMAGOViewModel = hiltViewModel<EMAGOViewModel>()) {


    LaunchedEffect(key1 = Unit) {
        vm.getAllChatData()
    }

    val inProgress = vm.inProcessChats
    if (inProgress.value) {
        CommonProgressBar()
    } else {
        val chats = vm.chats.value
        val userData = vm.userData.value
        val showDialog = remember {
            mutableStateOf(false)
        }
        val onFabClick: () -> Unit={showDialog.value=true}
        val onDismiss: () -> Unit={showDialog.value=false}
        val onAddChat = fun (title: String, description: String) {
            vm.onAddChat(title, description)
            showDialog.value=false
        }

        Scaffold (
            floatingActionButton = {
                FAB(
                showDialog = showDialog.value,
                onFabClick = onFabClick,
                onDismiss = onDismiss,
                onAddChat = onAddChat,
            )
            },
            content = {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    TitleText(text = "Chats")

                    if (chats!!.isEmpty()) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            Text(text = "채팅방이 존재하지 않습니다.")
                        }
                    } else {
                        ChatListBox(navController, modifier = Modifier.fillMaxWidth(), chatList = chats)
                    }


                    Spacer(modifier = Modifier.weight(1f)) // 남은 공간을 채워 BottomNavigationMenu를 아래로 밀어냅니다.
                }
            }
        )

        }
    }

@Composable

fun FAB(
    showDialog: Boolean, onFabClick: () -> Unit, onDismiss: () -> Unit, onAddChat: (String, String) -> Unit
) {
    val addChatTitle = remember {
        mutableStateOf("")
    }
    val addChatDescription = remember {
        mutableStateOf("")
    }

    if (showDialog) {
        AlertDialog(


            onDismissRequest = {
                onDismiss.invoke()
                addChatTitle.value = ""
                addChatDescription.value = ""
            },
            confirmButton = {

                Button(onClick = { onAddChat(addChatTitle.value, addChatDescription.value) }) {
                    Text(text = "Add Chat")
                }

            }, title = {
                Text(text = "Add Chat")
            },
            text = {
                Column {
                    Row {
                        Text(text = "제목")
                        OutlinedTextField(
                            value = addChatTitle.value,
                            onValueChange = { addChatTitle.value = it },
                        )
                    }
                    Row {
                        Text(text ="내용")
                        OutlinedTextField(
                            value = addChatDescription.value,
                            onValueChange = { addChatDescription.value = it },
                        )
                    }
                    

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
fun ChatListBox(navController: NavController, modifier: Modifier, chatList: List<ChatData> ){

    LazyColumn(modifier = modifier) {
        items(chatList) {
            item ->
            Column {
                Text(text = item.id, modifier = Modifier.clickable {
                    //val route = DestinationScreen.SingleChat.createRoute(item.id)
                    //navigateTo(navController, route)
                })
            }
        }

    }
}
