package ai.opensource.emago.screens

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
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun ChatCreateScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(color = Color(0xFFD0E8F2))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "채팅방 만들기")
        }
    }

    Box() {
        var chatRoomName by remember { mutableStateOf("") }
        var chatRoomDescription by remember { mutableStateOf("") }
        var isLimitOn by remember { mutableStateOf(false) }
        var limitNumber by remember { mutableStateOf("") }
        var isPrivateChat by remember { mutableStateOf(false) }
        var password by remember { mutableStateOf("") }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(55.dp)) // 공간 생성
                Text("채팅창 이미지 등록")

                Spacer(modifier = Modifier.height(10.dp)) // 공간 생성
                Image(
                    painter = painterResource(id = R.drawable.chatbot1),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp) // 이미지 크기 지정
                        .clip(CircleShape)
                )// 이미지를 원형으로 만듦// 프로필 사진
                Spacer(modifier = Modifier.height(10.dp)) // 공간 생성
                TextField(
                    value = chatRoomName,
                    onValueChange = { chatRoomName = it },
                    label = { Text("채팅방 이름") }) // 채팅방 이름 입력
                Spacer(modifier = Modifier.height(10.dp)) // 공간 생성
                TextField(
                    value = chatRoomDescription,
                    onValueChange = { chatRoomDescription = it },
                    label = { Text("채팅방 설명") }) // 채팅방 설명 입력
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("인원수 제한")
                    Spacer(modifier = Modifier.width(10.dp)) // 텍스트와 스위치 사이에 공간 추가
                    Switch(checked = isLimitOn, onCheckedChange = { isLimitOn = it })
                }
                Spacer(modifier = Modifier.height(10.dp)) // 공간 생성
                TextField(
                    value = limitNumber,
                    onValueChange = { limitNumber = it },
                    label = { Text("인원 수") }) // 인원 수 입력
                Spacer(modifier = Modifier.height(10.dp)) // 공간 생성
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { isPrivateChat = false },
                        enabled = isPrivateChat // 비공개 채팅이 선택되지 않았을 때만 활성화
                    ) {
                        Text("공개 채팅")
                    }
                    Spacer(modifier = Modifier.width(10.dp)) // 버튼 사이에 공간 추가
                    Button(
                        onClick = { isPrivateChat = true },
                        enabled = !isPrivateChat // 공개 채팅이 선택되지 않았을 때만 활성화
                    ) {
                        Text("비공개 채팅")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp)) // 공간 생성
                if (isPrivateChat) { // 비공개 채팅 선택시
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("비밀번호") }) // 비밀번호 입력
                }
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
                onClick = { /* 생성하기 버튼 클릭 시 수행할 동작 */ },
                modifier = Modifier.padding(bottom = 60.dp) // 아래쪽에 패딩 추가
            ) {
                Text("생성하기")
            }
        }
    }
}