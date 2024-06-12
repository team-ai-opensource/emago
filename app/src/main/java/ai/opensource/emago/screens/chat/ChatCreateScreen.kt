package ai.opensource.emago.screens.chat

import ai.opensource.emago.EMAGOViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ai.opensource.emago.R
import ai.opensource.emago.util.CommonImage
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.material3.Card
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ChatCreateScreen(navController: NavController, vm: EMAGOViewModel = hiltViewModel<EMAGOViewModel>()) {

    var chatRoomName by remember { mutableStateOf("") }
    var chatRoomDescription by remember { mutableStateOf("") }
    var chatImageUrl by remember {
        mutableStateOf("")
    }


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                vm.uploadImage(uri) {
                    chatImageUrl = it.toString()
                }
            }
        }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD0E8F2))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }

    Box() {


        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(55.dp)) // 공간 생성

                Spacer(modifier = Modifier.height(10.dp)) // 공간 생성
//                Image(
//                    painter = painterResource(id = R.drawable.chatbot1),
//                    contentDescription = "Profile Picture",
//                    modifier = Modifier
//                        .size(100.dp) // 이미지 크기 지정
//                        .clip(CircleShape)
//                )// 이미지를 원형으로 만듦// 프로필 사진
                Box(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min).clickable {
                    launcher.launch("image/*")
                })
                {
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(80.dp)
                    ) {
                        CommonImage(data = chatImageUrl)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp)) // 공간 생성
                TextField(
                    value = chatRoomName,
                    onValueChange = { chatRoomName = it },
                    label = { Text("채팅방 이름") }) // 채팅방 이름 입력
                Spacer(modifier = Modifier.height(20.dp)) // 공간 생성
                TextField(
                    value = chatRoomDescription,
                    onValueChange = { chatRoomDescription = it },
                    label = { Text("채팅방 설명") }) // 채팅방 설명 입력

            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp)) // 공간 생성
            Button(
                onClick = {
                    vm.onAddChat(chatRoomName, chatRoomDescription, chatImageUrl)
                    navController.navigate("chatList")
                },
                modifier = Modifier.padding(bottom = 60.dp) // 아래쪽에 패딩 추가
            ) {
                Text("생성하기")
            }
        }
    }
}