package ai.opensource.emago.screens

import ai.opensource.emago.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFCF8EC))
    ) {
        // Child views.
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .background(color = Color(0xFFFCF8EC))
        ) {
            // Child views.
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(375.dp)
                    .height(25.dp)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                // Child views.
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
                        text = "마이 페이지",
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
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(13.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(669.dp)
                .background(color = Color(0xFF79A3B1))
        ) {
            // Child views.
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(174.dp)
                    .background(color = Color(0xFFFCF8EC))
                    .padding(start = 26.dp, top = 22.dp, end = 26.dp, bottom = 11.dp)
            ) {
                // Child views.
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
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
//                        Image(
//                            painter = painterResource(id = R.drawable.dog_square),
//                            contentDescription = "image description",
//                            contentScale = ContentScale.FillBounds,
//                            modifier = Modifier
//                                .width(80.dp)
//                                .height(80.dp))

                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        // Child views.
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width(89.dp)
                                .height(15.dp)
                                .background(
                                    color = Color(0xB2456268),
                                    shape = RoundedCornerShape(size = 3.dp)
                                )

                        ) {
                            // Child views.
                            Text(
                                text = "초대코드 : 010101010",
                                style = TextStyle(
                                    fontSize = 8.sp,
                                    //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFFFFFFF),

                                    )
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(
                                7.dp,
                                Alignment.CenterVertically
                            ),
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            // Child views.
                            Text(
                                text = "동에번쩍서에번쩍레드로드라이트",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF000000),
                                )
                            )
                            Text(
                                text = "RRR@chungbuk.ac.kr",
                                style = TextStyle(
                                    fontSize = 8.sp,
                                    //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0x8A000000),
                                )
                            )
                            Text(
                                text = "어디든지나타난다동에번쩍서에번쩍",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                )
                            )

                        }
                    }
                }
                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF79A3B1)),
                    modifier = Modifier
                        .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
                        .width(256.dp)
                        .height(37.dp)) {
                    Text(
                        text = "계정 설정 / 프로필 편집",
                        style = TextStyle(
                            fontSize = 10.sp,
                            //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(187.dp)
                    .background(color = Color(0xFFFCF8EC))
                    .padding(start = 43.dp, top = 42.dp, end = 102.dp, bottom = 31.dp)
            ) {
                // Child views.
                Text(
                    text = "간략한 통계",
                    style = TextStyle(
                        fontSize = 15.sp,
                        //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(335.dp)
                    .background(color = Color(0xFFFCF8EC))
                    .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 20.dp)
            ) {
                // Child views.
                Row(
                    horizontalArrangement = Arrangement.spacedBy(13.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(width = 0.5.dp, color = Color(0x99000000))
                        .width(335.dp)
                        .height(59.dp)
                        .padding(start = 18.dp, top = 15.dp, end = 10.dp, bottom = 15.dp)
                ) {
                    // Child views.
                    Text(
                        text = "계정 설정",
                        style = TextStyle(
                            fontSize = 15.sp,
                            //fontFamily = FontFamily(Font(R.font.nanumsquareround)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                        )
                    )
                }

            }
        }
    }
}
