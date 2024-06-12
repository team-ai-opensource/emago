package ai.opensource.emago.screens.profile

import ai.opensource.emago.EMAGOViewModel
import ai.opensource.emago.R
import ai.opensource.emago.util.CommonImage
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
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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

@Composable
fun ProfileSettingScreen(
    navController: NavController,
    vm: EMAGOViewModel = hiltViewModel<EMAGOViewModel>()
) {
    val userData = vm.userData.value
    val userName = userData?.name?:""
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                vm.uploadProfileImage(uri)
            }
        }
    // User View
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFCF8EC))
    ) {
        // Body
        Column(
            verticalArrangement = Arrangement.spacedBy(13.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Profile Image
                val imageUrl = vm.userData.value?.imageUrl

                @Composable
                fun ProfileImage(imageUrl: String?, vm: EMAGOViewModel) {
                    Box(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min))
                    {
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
                    ProfileImage(imageUrl = imageUrl, vm = vm)
                }
                Row {
                    //프로필 사진 변경
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .background(
                                color = Color(0xFF79A3B1),
                                shape = RoundedCornerShape(size = 5.dp)
                            )
                            .padding(start = 10.dp, top = 3.dp, end = 10.dp, bottom = 3.dp)
                    ) {
                        Text(
                            text = "프로필 사진 변경",
                            modifier = Modifier
                                .clickable {
                                    launcher.launch("image/*")
                                },
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
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
                            .clickable { navController.navigate("ProfileSet") }
                    ) {
                        // Child views.
                        Text(
                            text = "닉네임",
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                                color = Color(0xFF000000),
                            )
                        )
                        Text(
                            text = userName,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
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
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                                color = Color(0xFF000000),
                            )
                        )
                        Text(
                            text = "RRR@chungbuk.ac.kr",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                            color = Color(0xFF999999),
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
                            .clickable { navController.navigate("setPW") }
                    ) {
                        // Child views.
                        Text(
                            text = "비밀번호 변경",
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
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
                .clickable { vm.logout() }
        ) {
            // Child views.
            Text(
                text = "로그아웃",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                    color = Color(0xFF000000),
                )
            )
        }
        //회원 탈퇴
        TextButton(onClick = { /*회원 탈퇴*/ }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(91.dp)
                .height(33.dp)
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
        ) {
                Text(
                    text = "회원 탈퇴",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                        color = Color(0xFF999999),
                    )
                )
            }
        }
    }
}