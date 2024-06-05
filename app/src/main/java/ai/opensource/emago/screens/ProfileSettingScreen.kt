package ai.opensource.emago.screens

import ai.opensource.emago.CommonImage
import ai.opensource.emago.CommonProgressBar
import ai.opensource.emago.EMAGOViewModel
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileSettingScreen(
    navController: NavController,
    vm: EMAGOViewModel
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(375.dp)
            .height(812.dp)
            .background(color = Color(0xFFFCF8EC))
    ) {
        // Header
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(375.dp)
                .height(25.dp)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            // Child views.
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        0.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .padding(start = 0.dp, end = 0.dp, bottom = 0.dp)
                ) {
                    // Child views.
                }
            }
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(293.dp)
                        .fillMaxHeight()
                ) {
                    // Child views.
                    Text(
                        text = "계정 설정",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 20.sp,
                            //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        0.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .padding(start = 0.dp, end = 0.dp, bottom = 0.dp)
                ) {
                    // Child views.
                }
            }
        }
        // Body
        Row {
            Column(
                verticalArrangement = Arrangement.spacedBy(13.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(669.dp)
                    .background(color = Color(0xFFFCF8EC))
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                // Child views.
                Row {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(343.dp)
                            .height(110.dp)
                    ) {
                        // Child views.
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                0.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)
                        ) {
                            // Child views.
                            @Composable
                            fun ProfileImage(imageUrl: String?, vm: EMAGOViewModel) {
                                Box(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min))
                                {
                                    Column(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Card(
                                            shape = CircleShape,
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .size(80.dp)
                                        ) {
                                            CommonImage(data = imageUrl)

                                        }
                                    }
                                }
                                if (vm.inProcess.value) {
                                    CommonProgressBar()
                                }
                            }
                        }
                        Row {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                    .width(99.dp)
                                    .height(22.dp)
                                    .background(
                                        color = Color(0xFF79A3B1),
                                        shape = RoundedCornerShape(size = 5.dp)
                                    )
                                    .padding(start = 11.dp, top = 3.dp, end = 8.dp, bottom = 4.dp)
                            ) {
                                // Child views.
                                val launcher =
                                    rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
                                        uri?.let {
                                            vm.uploadProfileImage(uri)
                                        }
                                    }
                                Text(
                                    text = "프로필 사진 변경",
                                    modifier = Modifier
                                        .clickable {
                                            launcher.launch("image/*")
                                        },
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFFFFFFFF),
                                    )
                                )
                            }
                        }
                    }
                }
                Row {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0x4D000000),
                                shape = RoundedCornerShape(size = 16.dp)
                            )
                            .width(343.dp)
                            .height(206.dp)
                    ) {
                        // Child views.
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                //.border(width = 1.dp, color = Color(0x4D000000))
                                .width(343.dp)
                                .height(51.dp)
                                .padding(start = 10.dp, top = 17.dp, end = 10.dp, bottom = 17.dp)
                        ) {
                            // Child views.
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .width(308.dp)
                                    .height(17.dp)
                                    .padding(start = 8.dp, end = 8.dp)
                                    .clickable { navController.navigate("") }
                            ) {
                                // Child views.
                                Text(
                                    text = "닉네임",
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )
                                Text(
                                    text = "레드로드라이트",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF999999),
                                    )
                                )
                            }
                        }
                        Divider(
                            color = Color(0x4D000000)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width(343.dp)
                                .height(51.dp)
                                .padding(start = 10.dp, top = 17.dp, end = 10.dp, bottom = 17.dp)
                        ) {
                            // Child views.
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .width(308.dp)
                                    .height(17.dp)
                                    .padding(start = 8.dp, end = 8.dp)
                            ) {
                                // Child views.
                                Text(
                                    text = "이름",
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )
                                Text(
                                    text = "홍길동",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF999999),
                                    )
                                )
                            }
                        }
                        Divider(
                            color = Color(0x4D000000)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width(343.dp)
                                .height(51.dp)
                                .padding(start = 10.dp, top = 17.dp, end = 10.dp, bottom = 17.dp)
                        ) {
                            // Child views.
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    139.dp,
                                    Alignment.Start
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .width(308.dp)
                                    .height(17.dp)
                                    .padding(start = 8.dp, end = 8.dp)
                            ) {
                                // Child views.
                                Text(
                                    text = "이메일",
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )
                                Text(
                                    text = "RRR@chungbuk.ac.kr",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF999999),
                                    )
                                )
                            }
                        }
                        Divider(
                            color = Color(0x4D000000)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width(343.dp)
                                .height(51.dp)
                                .padding(start = 10.dp, top = 17.dp, end = 10.dp, bottom = 17.dp)
                        ) {
                            // Child views.
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    185.dp,
                                    Alignment.Start
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .width(308.dp)
                                    .height(17.dp)
                                    .padding(start = 8.dp, end = 8.dp)
                                    .clickable { navController.navigate("") }
                            ) {
                                // Child views.
                                Text(
                                    text = "비밀번호 변경",
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )
                            }
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0x4D000000),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .width(343.dp)
                        .height(49.dp)
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    // Child views.
                    Text(
                        text = "로그아웃",
                        modifier = Modifier.clickable {
                            vm.logout()
                        },
                        style = TextStyle(
                            fontSize = 13.sp,
                            //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(91.dp)
                        .height(33.dp)
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    // Child views.
                    Text(
                        text = "회원 탈퇴",
                        style = TextStyle(
                            fontSize = 13.sp,
                            //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF999999),
                        )
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfileSettingScreenPreview() {
//    ProfileSettingScreen()
//}