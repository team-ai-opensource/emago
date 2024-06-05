package ai.opensource.emago.screens.chat


import ai.opensource.emago.util.CommonProgressBar
import ai.opensource.emago.DestinationScreen
import ai.opensource.emago.EMAGOViewModel
import ai.opensource.emago.util.TitleText
import ai.opensource.emago.data.ChatData
import ai.opensource.emago.util.navigateTo
import ai.opensource.emago.screens.BottomNavigationItem
import ai.opensource.emago.screens.BottomNavigationMenu
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import ai.opensource.emago.R



@Composable
fun ChatListScreen(navController: NavController, vm: EMAGOViewModel) {

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
                    BottomNavigationMenu(
                        selectedItem = BottomNavigationItem.CHATLIST, navController = navController
                    )
                }
            }
        )

        }
    }


@Composable
fun dialog(data: ChatData, navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var chatname by remember { mutableStateOf("") }
    var chatexplain by remember { mutableStateOf("") }
    var chatnumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                                          showDialog = true
            },
            text = data.title!!)
    }




    if (showDialog) {
        Dialog(
            onDismissRequest = {
                showDialog = false
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            // Dialog의 배경을 투명하게 설정합니다.
            Surface(
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(15.dp),
                color = Color.Transparent
            ) {
                // 여기서 Box의 배경색을 원하는 색으로 설정합니다.
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                        .background(color = Color(0xFF79A3B1), shape = RoundedCornerShape(16.dp))

                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .width(291.dp)
                            .height(132.dp)
                            .padding(top = 16.dp, bottom = 16.dp)
                    ) {
                        //
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .width(291.dp)
                                .height(100.dp)
                                .padding(start = 84.dp, end = 84.dp)
                        ) {
                            // Child views.
                            Image(
                                painter = painterResource(id = R.drawable._main_icon),
                                contentDescription = "image description",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.clip(RoundedCornerShape(50))
                            )
                        }
                    }


                    Text(data.title!!)

                    Text(data.description!!)

//                    Row(
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 8.dp, bottom = 8.dp)
//                            .clickable { }
//                    ) {
//                        // Child views.
//                        Text(
//                            text = "참가 인원",
//                            style = TextStyle(
//                                fontSize = 15.sp,
//                                lineHeight = 20.sp,
//                                //fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
//                                fontWeight = FontWeight(400),
//                                color = Color(0xFF000000),
//                            ),
//                            modifier = Modifier.padding(start = 10.dp)
//                        )
//                        OutlinedTextField(value = chatnumber,
//                            onValueChange = { chatnumber = it },
//                            singleLine = true,
//                            placeholder = { Text("인원") })
//
//                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            0.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                spotColor = Color(0x40000000),
                                ambientColor = Color(0x40000000)
                            )
                            .width(90.dp)
                            .height(30.dp)
                            .background(
                                color = Color(0xFFFCF8EC),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .padding(start = 10.dp, top = 8.dp, end = 10.dp, bottom = 7.dp)
                    ) {
                        // Child views.
                        Text(
                            text = "참가하기",
                            modifier = Modifier.clickable {
                                val route = DestinationScreen.SingleChat.createRoute(data.id)
                                navigateTo(navController, route)
                                                          },
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                // fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.12.sp,
                            )
                        )
                    }
                }
            }
        }
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
                Column (
                    verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(291.dp)
                        .height(200.dp)
                        .padding(top = 4.dp, bottom = 4.dp)

                ){
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
                dialog(item, navController)
            }
        }

    }
}
