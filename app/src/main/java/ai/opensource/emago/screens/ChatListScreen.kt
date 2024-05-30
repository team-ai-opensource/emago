package ai.opensource.emago.screens


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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatListScreen(navController: NavController) {
    Scaffold { innerPadding ->
        //user View
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFCF8EC))
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(13.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // My Chat Room
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 5.dp)
                ) {
                    Text(
                        text = "내 채팅방",
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                            color = Color(0xFF000000),
                            letterSpacing = 0.15.sp,
                        )
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ChatroomList(false, "영어공부방", R.drawable.dog_square)
                        ChatroomList(false, "성공의채팅방", R.drawable.handsom_guy)
                        ChatroomList(false, "33333", R.drawable.woman)
                        ChatroomList(false, "여친구함", R.drawable.soldier)
                    }
                }
                // Recommended Chat Room
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 5.dp)
                ) {
                    Text(
                        text = "추천 채팅방",
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                            color = Color(0xFF000000),
                            letterSpacing = 0.15.sp,
                        )
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ChatroomList(false, "영어공부방", R.drawable.dog_square)
                        ChatroomList(false, "성공의채팅방", R.drawable.handsom_guy)
                        ChatroomList(false, "33333", R.drawable.woman)
                        ChatroomList(false, "여친구함", R.drawable.soldier)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { navController.navigate(DestinationScreen.ChatCreate.route) }) {
                    Text(text = "채팅방 만들기",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(DestinationScreen.ChatCreate.route)
                            }
                    )
                }
            }


        }
    }
}
@Composable
fun ChatroomList(isSearch : Boolean, roomName : String, id: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Child views.


        Row(
            modifier = Modifier
                .width(52.dp)
                .height(64.dp)
        ) {
            Image(
                painter = painterResource(id = id),
                contentDescription = "Profile Picture",
                modifier = Modifier

                    .clip(CircleShape)
            )

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Child views.
            Text(
                text = roomName,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                    color = Color(0xFF000E08),
                )
            )
            Text(
                text = "최근 채팅 내역",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                    color = Color(0xFF797C7B),
                )
            )
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            // Child views.
            Text(
                text = if (isSearch) "3분전" else "2/40",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                    color = Color(0xFF000000),
                )

            )
        }
    }
}
//@Preview
//@Composable
//fun ChatListScreenPreview() {
//    ChatListScreen()
//}