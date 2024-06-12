package ai.opensource.emago.screens.chat

import ai.opensource.emago.EMAGOViewModel
import ai.opensource.emago.R
import ai.opensource.emago.data.ChatUser
import ai.opensource.emago.data.Message
import ai.opensource.emago.util.extractTimeFromTimestampString
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ChatScreen(navController: NavController, vm: EMAGOViewModel = hiltViewModel(), chatID : String) {

    var inputChat by rememberSaveable{ mutableStateOf("") }
    val onSendChat = {
        vm.onSendReply(chatID, inputChat)
        inputChat = ""
    }
    val myUser = vm.userData.value
    val chatMessages = vm.chatMessages
    val showDialog = remember{ mutableStateOf(false) }
    val chatText = rememberSaveable{ mutableStateOf("") }
    val advancedSentence = rememberSaveable { mutableStateOf("") }
    val errorSentence = rememberSaveable { mutableStateOf("") }
    val correctSentence = rememberSaveable { mutableStateOf("") }

    val onMessageClick = fun(
        messageComment: String?,
        messageAdvancedSentence: String?,
        messageErrorSentence: String?,
        messageCorrectSentence: String?
    ) {
        chatText.value = messageComment?:""
        advancedSentence.value = messageAdvancedSentence?:""
        errorSentence.value = messageErrorSentence?:""
        correctSentence.value = messageCorrectSentence?:""

        showDialog.value = true
    }
    val onDismiss: () -> Unit = { showDialog.value = false }

    LaunchedEffect(key1 = Unit) {
        vm.populateMessages(chatID)
    }
    BackHandler {
        vm.depopulateMessage()
    }
    FeedbackChatDialog(showDialog.value, onDismiss, chatText.value, advancedSentence.value, errorSentence.value, correctSentence.value)

    Scaffold(
        topBar = { ChatTopBar(navController) },
        bottomBar = { ChatBottomBar(inputChat, {inputChat = it}, onSendChat ) }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFD0E8F2))
                .padding(innerPadding)
        ) {
            // Chat messages
            ChatBox(
                chatMessages.value ?: listOf(),
                onMessageClick,
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF79A3B1),
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        title = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Image

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.Start,
                ) {
                    // Child views.
                    Text(
                        text = "CBNU_CS(채팅방 제목)",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                            color = Color(0xFF000E08),
                        )
                    )
                    Row {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Chatroom Member",
                            tint = Color(0xFF797C7B),
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "20",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                                color = Color(0xFF797C7B),
                            )
                        )

                    }
                }

            }

        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
    )
}

@Composable
fun ChatBottomBar(input : String, onInputChange : (String) -> Unit, onSendInput: () -> Unit) {
    BottomAppBar(
        actions = {
            TextField(
                value = input,
                onValueChange = onInputChange,
                placeholder = {
                    Text(text = "메시지를 입력하세요")
                },
                singleLine = true,
            )
            IconButton(onClick = onSendInput) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ChatMessage(
    isSYS: Boolean = false,
    isUser: Boolean = false,
    isFirst: Boolean = true,
    isTimeChange: Boolean = true,
    user: ChatUser = ChatUser(),
    message: String? = "메시지",
    timestamp: String? = "00:00 AM",
    onMessageClick: () -> Unit = {},
    modifier: Modifier,
    isAIReady: Boolean
) {
    Row(
        horizontalArrangement = if(isSYS) {
            Arrangement.Center
        } else {
            if (isUser) {
                Arrangement.End
            } else {
                Arrangement.Start
            }
        },
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        if (isSYS) {
                // Child views.
                Row(
                    horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .background(
                            color = Color(0x26000000),
                            shape = RoundedCornerShape(size = 34.dp)
                        )
                        .padding(start = 10.dp, top = 4.dp, end = 9.dp, bottom = 4.dp)
                ) {
                    // Child views.
                    Text(
                        text = "0000년 0월 0일 월요일",
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                            color = Color(0xFFF8F8F9),
                        )
                    )
                }

        } else {

                // Profile image
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        0.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.size(27.dp)
                ) {
                    // TEMP
                    if (!isUser && isFirst) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Image",
                            tint = Color(0xFF000000),
                            modifier = Modifier.size(27.dp)
                        )
                    } else{
                        Spacer(modifier = Modifier.size(27.dp))
                    }
                }

            // Chat Container
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                if(!isUser && isFirst) {
                    // Name
                    Text(
                        text = "이름",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                            color = Color(0x80000000),
                        )
                    )
                }
                // Chat message
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    // Child views.
                    if(!isUser) {
                        ChatBox(isUser, isAIReady, isTimeChange, message)
                        ChatInfoBox(isUser, isAIReady, isTimeChange, timestamp)
                    } else {
                        ChatInfoBox(isUser, isAIReady, isTimeChange, timestamp)
                        ChatBox(isUser, isAIReady, isTimeChange, message)
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBox(
    isUser: Boolean = false,
    isFirst: Boolean = true,
    isTimeChange: Boolean = true,
    message : String? = "메시지"
) {
    // Chat Box
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(
                color = if (isUser) {
                    Color(0xFF79A3B1)
                } else {
                    Color(0xFFFCF8EC)
                },
                shape = RoundedCornerShape(
                    topStart = if ((isUser) || (!isUser && !isFirst)) 10.dp else 0.dp,
                    topEnd = if ((isUser && !isFirst) || (!isUser)) 10.dp else 0.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
            .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
    ) {
        // Child views.
        Text(
            text = message?:"",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                color = Color(0xFF000000),
            )
        )
    }
}

@Composable
fun ChatInfoBox(
    isUser: Boolean = false,
    isAIReady: Boolean = false,
    isTimeChange: Boolean = true,
    timestamp: String? = "00:00 AM"
) {
    // Chat Info Box
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .width(46.dp)
            .fillMaxWidth()
    ) {
        // Child views.
        Row(
            horizontalArrangement = if(isUser) {
                Arrangement.spacedBy(10.dp, Alignment.End)
            } else {
                Arrangement.spacedBy(10.dp, Alignment.Start)
            },
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()

        ){
            if(isAIReady){
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Profile Image",
                    tint = Color(0xFFFF7100),
                    modifier = Modifier.size(10.dp)
                )
            }else{
                Spacer(modifier = Modifier.size(10.dp))
            }
        }

        if(isTimeChange){
            Text(
                text = timestamp?:"",
                style = TextStyle(
                    fontSize = 8.sp,
                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                    color = Color(0xFF000000),
                )
            )
        }else {
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun ChatBox(
    chatMessages: List<Message>,
    onMessageClick: (String?, String?, String?, String?) -> Unit,
    modifier: Modifier,
    vm: EMAGOViewModel = hiltViewModel<EMAGOViewModel>()
){
    LazyColumn(modifier = modifier) {
        items(chatMessages){ msg->
            // Timestamp를 Date로 변환
            val date: Date = msg.timestamp!!.toDate()

            // 날짜 형식화 예시
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)
            val formattedDate: String = sdf.format(date)

            ChatMessage(
                isUser = msg.user.userId == vm.userData.value?.userId,
                user = msg.user,
                message = msg.message,
                timestamp = formattedDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onMessageClick(
                            msg.feedback?.comment,
                            msg.feedback?.advanced_sentence,
                            msg.feedback?.error_sentence,
                            msg.feedback?.correct_sentence
                        )
                    },
                isAIReady = !msg.feedback?.comment.isNullOrEmpty()
            )
        }
    }
}

@Composable
fun FeedbackChatDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    comment: String,
    advancedSentence: String,
    errorSentence: String,
    correctSentence: String,
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

                Button(onClick = { onDismiss() }) {
                    Text(text = "닫기")
                }

            }, title = {
                Text(text = "AI피드백")
            },
            text = {
                Box(modifier = Modifier
                    .width(283.dp)
                    .height(280.dp)
                    .background(color = Color(0xFFFCF8EC), shape = RoundedCornerShape(size = 8.dp))
                    .padding(top = 16.dp, bottom = 16.dp))
                {
                    Column {
                        Row {
                            Text(text = comment)
                        }
                        Row {
                            Text(text = advancedSentence)

                        }
                        Row {
                            Text(text = errorSentence)

                        }
                        Row {
                            Text(text = correctSentence)

                        }
                    }
                }

            }
        )
    }
}
