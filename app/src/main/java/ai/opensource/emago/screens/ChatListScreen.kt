package ai.opensource.emago.Screens

import ai.opensource.emago.DestinationScreen
import ai.opensource.emago.R
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatListScreen(navController: NavController) {

        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "채팅방 목록",
            )
            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(
                            text = "내 채팅방",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontSize = 10.sp
                        )
                        Image(
                            painter = painterResource(id = R.drawable.dog),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(
                            text = "추천 채팅방",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontSize = 10.sp
                        )
                        Image(
                            painter = painterResource(id = R.drawable.dog),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
            Text(text = "계정이 있나요 ? 로그인 하러 가기 - >",
                color = Color.Blue,
                modifier = Modifier.padding(8.dp).clickable {
                    navController.navigate(DestinationScreen.Login.route)
                }
            )
            Text(text = "채팅방 만들기",
                color = Color.Blue,
                modifier = Modifier.padding(8.dp).clickable {
                    navController.navigate(DestinationScreen.ChatCreate.route)
                }
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Modifier
                .width(375.dp)
                .height(53.dp)
                .background(color = Color(0xFFFFFFFF))
                .padding(start = 50.dp, top = 10.dp, end = 50.dp, bottom = 10.dp)
        }

}
