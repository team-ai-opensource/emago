package ai.opensource.emago.screens.home

import ai.opensource.emago.R
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@Composable
fun ReviewScreen() { // TODO : 선택한 날짜 받아오기
    val currentDate = remember { LocalDate.now() }
    // Review Column
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD0E8F2))
    ) {
        // header

        //HeaderContents() Todo : Add header contents

        // body
        Column(
            verticalArrangement = Arrangement.spacedBy(64.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 32.dp, end = 32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0x8079A3B1),
                            shape = RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                ) {
                    Calendar(currentDate) // TODO : 함수 파라미터로 선택한 날짜 넘겨 받고 그 날짜에 해당하는 달력을 보여줌
                }
                // Review Box
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(animationSpec = tween(durationMillis = 300))
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                        .background(
                            color = Color(0xFF79A3B1),
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp
                            )
                        )
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Bottom),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFFCF8EC),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .padding(
                                start = 8.dp,
                                top = 16.dp,
                                end = 8.dp,
                                bottom = 16.dp
                            )
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "Review",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                fontSize = 24.sp,
                                color = Color(0xFF79A3B1)
                            )
                        )
                        // Review List
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 400.dp)
                        ) {
                            ReviewContent()
                            ReviewContent(true)
                            ReviewContent()
                            ReviewContent()
                            ReviewContent()
                            ReviewContent()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewCard(){
    // Review Card
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFCF8EC),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .padding(
                start = 8.dp,
                top = 16.dp,
                end = 8.dp,
                bottom = 16.dp
            )
            .clickable { }
    ) {
        Text(
            text = "원문",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                fontSize = 18.sp,
                color = Color(0xFF79A3B1)
            )
        )
        Text(
            text = "수정된 내용",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                fontSize = 18.sp,
                color = Color(0xFF79A3B1)
            )
        )
        Text(
            text = "이유",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                fontSize = 18.sp,
                color = Color(0xFF79A3B1)
            )
        )
        // Review Button
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF79A3B1),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .padding(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
                .clickable { }
        ) {
            Text(
                text = "다음으로",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                    fontSize = 18.sp,
                    color = Color(0xFFFCF8EC)
                )
            )
        }
    }
}

@Preview
@Composable
fun ReviewScreenPreview() {
    ReviewScreen()
}

