package ai.opensource.emago.Screens

import ai.opensource.emago.R
import ai.opensource.emago.Screens.BottomNavigationMenu
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen (navController: NavController){


        // User views
        Column(
            verticalArrangement = Arrangement.spacedBy((-1).dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFD0E8F2))
                .verticalScroll(rememberScrollState())
        ) {
            // header
            //HeaderContents()
            // body
            Column(
                verticalArrangement = Arrangement.spacedBy(64.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 32.dp, end = 32.dp)
            ) {
                // Today's goal
                Column(
                    verticalArrangement = Arrangement.spacedBy((-10).dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Emago Robot
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Emago Robot",
                        contentScale = ContentScale.None
                    )
                    // Today's goal Box
                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                spotColor = Color(0x40000000),
                                ambientColor = Color(0x40000000)
                            )
                            .background(
                                color = Color(0xFF79A3B1),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(start = 12.dp, top = 12.dp, end = 12.dp)
                        ) {
                            // Progress Box
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = Color(0xFF456268),
                                        shape = RoundedCornerShape(size = 5.dp)
                                    )
                                    .fillMaxWidth()
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        12.dp,
                                        Alignment.Top
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(
                                            start = 16.dp,
                                            top = 12.dp,
                                            end = 16.dp,
                                            bottom = 12.dp
                                        )
                                ) {
                                    // Text Fields
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        // Text : Today's goal
                                        Text(
                                            text = "오늘의 목표",
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                lineHeight = 20.sp,
                                                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                fontWeight = FontWeight(700),
                                                color = Color(0xFFFFFFFF),
                                                textAlign = TextAlign.Center,
                                            )
                                        )
                                        // Percentage
                                        Text(
                                            text = "50%", // Todo : sync with data
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                lineHeight = 20.sp,
                                                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                fontWeight = FontWeight(700),
                                                color = Color(0xFFFFFFFF),
                                                textAlign = TextAlign.Center,
                                            )
                                        )
                                    }
                                    // Progress Bar
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(
                                            10.dp,
                                            Alignment.Start
                                        ),
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(10.dp)
                                            .background(
                                                color = Color(0xFFFCF8EC),
                                                shape = RoundedCornerShape(size = 10.dp)
                                            )
                                            .padding(
                                                start = 10.dp,
                                                top = 10.dp,
                                                end = 10.dp,
                                                bottom = 10.dp
                                            )
                                    ) {
                                        // Child views. todo : progress bar, sync with data
                                    }
                                }
                            }
                            if(true){
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xFF456268),
                                            shape = RoundedCornerShape(size = 5.dp)
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(
                                            12.dp,
                                            Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .padding(
                                                start = 16.dp,
                                                top = 12.dp,
                                                end = 16.dp,
                                                bottom = 12.dp
                                            )
                                    ) {
                                        // Text Fields
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            // Text : Today's goal
                                            Text(
                                                text = "오늘의 목표",
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    lineHeight = 20.sp,
                                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                    fontWeight = FontWeight(700),
                                                    color = Color(0xFFFFFFFF),
                                                    textAlign = TextAlign.Center,
                                                )
                                            )
                                            // Percentage
                                            Text(
                                                text = "50%", // Todo : sync with data
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    lineHeight = 20.sp,
                                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                    fontWeight = FontWeight(700),
                                                    color = Color(0xFFFFFFFF),
                                                    textAlign = TextAlign.Center,
                                                )
                                            )
                                        }
                                        // Progress Bar
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(
                                                10.dp,
                                                Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(10.dp)
                                                .background(
                                                    color = Color(0xFFFCF8EC),
                                                    shape = RoundedCornerShape(size = 10.dp)
                                                )
                                                .padding(
                                                    start = 10.dp,
                                                    top = 10.dp,
                                                    end = 10.dp,
                                                    bottom = 10.dp
                                                )
                                        ) {
                                            // Child views. todo : progress bar, sync with data
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xFF456268),
                                            shape = RoundedCornerShape(size = 5.dp)
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(
                                            12.dp,
                                            Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .padding(
                                                start = 16.dp,
                                                top = 12.dp,
                                                end = 16.dp,
                                                bottom = 12.dp
                                            )
                                    ) {
                                        // Text Fields
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            // Text : Today's goal
                                            Text(
                                                text = "오늘의 목표",
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    lineHeight = 20.sp,
                                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                    fontWeight = FontWeight(700),
                                                    color = Color(0xFFFFFFFF),
                                                    textAlign = TextAlign.Center,
                                                )
                                            )
                                            // Percentage
                                            Text(
                                                text = "50%", // Todo : sync with data
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    lineHeight = 20.sp,
                                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                    fontWeight = FontWeight(700),
                                                    color = Color(0xFFFFFFFF),
                                                    textAlign = TextAlign.Center,
                                                )
                                            )
                                        }
                                        // Progress Bar
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(
                                                10.dp,
                                                Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(10.dp)
                                                .background(
                                                    color = Color(0xFFFCF8EC),
                                                    shape = RoundedCornerShape(size = 10.dp)
                                                )
                                                .padding(
                                                    start = 10.dp,
                                                    top = 10.dp,
                                                    end = 10.dp,
                                                    bottom = 10.dp
                                                )
                                        ) {
                                            // Child views. todo : progress bar, sync with data
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xFF456268),
                                            shape = RoundedCornerShape(size = 5.dp)
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(
                                            12.dp,
                                            Alignment.Top
                                        ),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .padding(
                                                start = 16.dp,
                                                top = 12.dp,
                                                end = 16.dp,
                                                bottom = 12.dp
                                            )
                                    ) {
                                        // Text Fields
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            // Text : Today's goal
                                            Text(
                                                text = "오늘의 목표",
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    lineHeight = 20.sp,
                                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                    fontWeight = FontWeight(700),
                                                    color = Color(0xFFFFFFFF),
                                                    textAlign = TextAlign.Center,
                                                )
                                            )
                                            // Percentage
                                            Text(
                                                text = "50%", // Todo : sync with data
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    lineHeight = 20.sp,
                                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                    fontWeight = FontWeight(700),
                                                    color = Color(0xFFFFFFFF),
                                                    textAlign = TextAlign.Center,
                                                )
                                            )
                                        }
                                        // Progress Bar
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(
                                                10.dp,
                                                Alignment.Start
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(10.dp)
                                                .background(
                                                    color = Color(0xFFFCF8EC),
                                                    shape = RoundedCornerShape(size = 10.dp)
                                                )
                                                .padding(
                                                    start = 10.dp,
                                                    top = 10.dp,
                                                    end = 10.dp,
                                                    bottom = 10.dp
                                                )
                                        ) {
                                            // Child views. todo : progress bar, sync with data
                                        }
                                    }
                                }
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.Bottom,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.chevron_down),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.None
                                    )
                                }
                            }
                            // Button Fields
                            else Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                                verticalAlignment = Alignment.Bottom,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.chevron_down),
                                    contentDescription = "image description",
                                    contentScale = ContentScale.None
                                )
                            }
                        }
                    }
                }
                // Review
                Column(
                    verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Date Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color(0x8079A3B1), shape = RoundedCornerShape(size = 10.dp))
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Bottom),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Date
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 12.dp, top = 7.dp, end = 12.dp, bottom = 7.dp)
                            ) {
                                // Sunday
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                        .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                                ) {
                                    // Child views.
                                    Text(
                                        text = "7",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                }
                                // Monday
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                        .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                                ) {
                                    // Child views.
                                    Text(
                                        text = "7",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                }
                                // Tuesday
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                        .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                                ) {
                                    // Child views.
                                    Text(
                                        text = "7",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                }
                                // Wednesday
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                        .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                                ) {
                                    // Child views.
                                    Text(
                                        text = "7",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                }
                                // Thursday
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                        .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                                ) {
                                    // Child views.
                                    Text(
                                        text = "7",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                }
                                // Friday
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                        .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                                ) {
                                    // Child views.
                                    Text(
                                        text = "7",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                }
                                // Saturday
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                        .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                                ) {
                                    // Child views.
                                    Text(
                                        text = "7",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                }
                            }
                            // Today
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                                verticalAlignment = Alignment.Bottom,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp, top = 5.dp, end = 8.dp, bottom = 5.dp)
                            ) {
                                // Child views.
                                Text(
                                    text = "0월 0일",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )
                            }
                        }
                    }
                    // Review Box
                    Box(modifier = Modifier
                        .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
                        .fillMaxWidth()
                        .background(color = Color(0xFF79A3B1), shape = RoundedCornerShape(size = 10.dp))
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                    ){
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Child views.
                            Box{
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        4.dp,
                                        Alignment.Bottom
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color(0xFFFCF8EC),
                                            shape = RoundedCornerShape(size = 8.dp)
                                        )
                                        .padding(
                                            start = 73.dp,
                                            top = 8.dp,
                                            end = 73.dp,
                                            bottom = 4.dp
                                        )
                                ) {
                                    // Child views.
                                    /*
                                    Text(
                                        text = "오늘의 복습 시작",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                        )
                                    )
                                    Text(
                                        text = "오늘 남은 복습 : $",
                                        style = TextStyle(
                                            fontSize = 13.sp,
                                            lineHeight = 20.sp,
                                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                        )
                                    )
                                    */
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                                        horizontalAlignment = Alignment.Start,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        // Child views.
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                                            verticalAlignment = Alignment.Top,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            // Child views.
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                                                verticalAlignment = Alignment.Top,
                                            ) {
                                                // Child views.
                                                Text(
                                                    text = "(index)",
                                                    style = TextStyle(
                                                        fontSize = 13.sp,
                                                        lineHeight = 20.sp,
                                                        fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                        fontWeight = FontWeight(700),
                                                        color = Color(0xFF707070),
                                                    )
                                                )
                                                Text(
                                                    text = "채팅방이름",
                                                    style = TextStyle(
                                                        fontSize = 13.sp,
                                                        lineHeight = 20.sp,
                                                        fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                        fontWeight = FontWeight(700),
                                                        color = Color(0xFF707070),
                                                    )
                                                )
                                            }
                                            Text(
                                                text = "시간정보",
                                                style = TextStyle(
                                                    fontSize = 13.sp,
                                                    lineHeight = 20.sp,
                                                    //fontFamily = FontFamily(Font(R.font.nanumsquareroundr),
                                                    fontWeight = FontWeight(700),
                                                    color = Color(0xFF707070),
                                                )
                                            )
                                        }
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                                            verticalAlignment = Alignment.Top,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            // Child views.
                                            Text(
                                                text = "원문 내용",
                                                style = TextStyle(
                                                    fontSize = 15.sp,
                                                    lineHeight = 20.sp,
                                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                    fontWeight = FontWeight(700),
                                                    color = Color(0xFF000000),
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }