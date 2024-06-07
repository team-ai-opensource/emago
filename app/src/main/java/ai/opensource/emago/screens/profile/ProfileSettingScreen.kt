package ai.opensource.emago.screens.profile

import ai.opensource.emago.EMAGOViewModel
import ai.opensource.emago.util.PreviewNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ProfileSettingScreen(
    navController: NavController,
    vm: EMAGOViewModel = hiltViewModel<EMAGOViewModel>()
) {
    // User View
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
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
                    // ProfileImage() TODO : 함수가 다시 선언되어 있던데, 이거도 예제 확인 다시 부탁드립니다..
                }
                Row {// TODO : Column 밑에 Row가 있을 필요 없어요. Column 바로 나와도 됩니다.
                    // TODO : 이부분도 Button으로 변경 필요.
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .background(
                                color = Color(0xFF79A3B1),
                                shape = RoundedCornerShape(size = 5.dp)
                            )
                            .padding(start = 11.dp, top = 3.dp, end = 8.dp, bottom = 4.dp)
                    ) {
                        // Child views.
//                                val launcher =
//                                    rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
//                                        uri?.let {
//                                            vm.uploadProfileImage(uri)
//                                        }
//                                    }
                        // TODO : 여기 아마 버튼을 눌렀을때 동작인 것 같은데, 예제 코드 참고 후에 다시 확인 부탁드립니다.
                        Text(
                            text = "프로필 사진 변경",
                            modifier = Modifier
                                .clickable {
                                    //launcher.launch("image/*")
                                },
                            style = TextStyle(
                                fontSize = 11.sp,
                                //fontFamily = FontFamily(Font(R.font.nanumsquareround)), // TODO : nanumsquareround 뒤에 b 붙여야 합니다. (nanumsquareroundb)
                                fontWeight = FontWeight(700), // TODO : FontWeight가 200 은 l, 400은 r, 700은 b, 800은 eb 입니다. 이 줄은 이후에 지워주세요
                                color = Color(0xFFFFFFFF),
                            )
                        )

                    }
                }
            }
        }
        Row {// TODO : 여기 Row도
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0x4D000000),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .width(343.dp) // TODO : Figma에 Development 모드로 보면 이렇게 fixed된 값으로 나오는데, 모드를 끄면 fill모드로 나와있을꺼에요. fill은 .fillMaxWidth(), hug는 modify 안넣으시면 됩니다.
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
                            //.clickable { navController.navigate("ProfileSet") } TODO : 닉네임 변경 화면 구현 후 이동
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
                            text = "레드로드라이트", // TODO : ViewModel에서 가져오는 닉네임(이름)으로 바꿔야 합니다. 예제 참고해 주세요
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
                // TODO : 일단 이름부분은 지웠습니다. DB에서 닉네임, 이름 구분을 안해서...
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
                            text = "RRR@chungbuk.ac.kr", // TODO : 여기도 DB에서 가져오기
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
                        // .clickable { navController.navigate("") } TODO : 비번 변경창 구현 후 이동
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
                .clickable { vm.logout() } // TODO : clickable 속성 여기로 옮겼어요
        ) {
            // Child views.
            Text(
                text = "로그아웃",
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
            // TODO : 여기는 Material Theme에 있는 TextButton으로 변경하면 좋을듯. 로그인화면(FirstScreen)에 썼던거 있으니깐 참고해주세요
            Text( // TODO : 근데 아마 기능 자체는 구현 안되어 있을꺼라, 버튼 누른 후 실행에 주석처리 해두면 될듯
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

@Preview
@Composable
fun ProfileSetPreview(){
    val navController = PreviewNavController() // TODO : 이거로 다른 화면에서도 프리뷰 쓸 수 있습니다..안되면 쩔수 없음..
    ProfileSettingScreen(navController)
}