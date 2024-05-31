package ai.opensource.emago.Screens

import ai.opensource.emago.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDayPosition
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.yearMonth
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun HomeScreen(navController: NavController) {
    var showExtraItems by remember{ mutableStateOf(false)} // 빌드 할때는 false로 바꾸기
    var startReview by remember{ mutableStateOf(false)}
//    var startReview by remember{ mutableStateOf(true)}
    val currentDate = remember { LocalDate.now() }
    val currentMonth = remember(currentDate) { currentDate.yearMonth }
    val startMonth = remember(currentDate) { currentMonth.minusMonths(12) }
    val endMonth = remember(currentDate) { currentMonth.plusMonths(12) }
    val selections = remember { mutableStateListOf<LocalDate>() }
    val daysOfWeek = remember { daysOfWeek() }

    var isWeekMode by remember { mutableStateOf(false) }
    var isAnimating by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    // User views
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD0E8F2))
            .verticalScroll(rememberScrollState())
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
            //Today's Goal Column
            AnimatedVisibility(visible = !startReview) {
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
                            ItemRow("오늘의 목표", "50%")
                            AnimatedVisibility(visible = showExtraItems) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(start = 12.dp, top = 12.dp, end = 12.dp)) {
                                    ItemRow("영어로 보낸 메시지 수", "50%")
                                    ItemRow("채팅에 참가한 시간", "50%")
                                    ItemRow("복습한 채팅 수", "50%")
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
                                        verticalAlignment = Alignment.Top,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        // Child views.
                                        Button(
                                            onClick = { /*TODO*/ },
                                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCF8EC)),
                                            shape = RoundedCornerShape(size = 10.dp),
                                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
                                            modifier = Modifier
                                                .width(90.dp)
                                                .height(30.dp)
                                        ) {
                                            Text(
                                                text = "일자별 통계",
                                                style = TextStyle(
                                                    fontSize = 12.sp,
                                                    lineHeight = 12.sp,
                                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                                    color = Color(0xFF000000),
                                                    textAlign = TextAlign.Center,
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                                verticalAlignment = Alignment.Bottom,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(30.dp)
                                    .clickable { showExtraItems = !showExtraItems }
                            ) {
                                Image(
                                    painter = painterResource(id = if(showExtraItems) R.drawable.chevron_up else R.drawable.chevron_down),
                                    contentDescription = "arrow",
                                    contentScale = ContentScale.None,
                                )
                            }
                        }
                    }
                }
            }

            // Review Column
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Date Box
                Box(
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
                    Column(
                        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Bottom),
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
                        // Date
                        AnimatedVisibility(visible = startReview) {
                            // Calendar
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                val monthState = rememberCalendarState(
                                    startMonth = startMonth,
                                    endMonth = endMonth,
                                    firstVisibleMonth = currentMonth,
                                    firstDayOfWeek = daysOfWeek.first(),
                                )
                                val weekState = rememberWeekCalendarState(
                                    startDate = startMonth.atStartOfMonth(),
                                    endDate = endMonth.atEndOfMonth(),
                                    firstVisibleWeekDate = currentDate,
                                    firstDayOfWeek = daysOfWeek.first(),
                                )
                                CalendarHeader(daysOfWeek = daysOfWeek)
                                AnimatedVisibility(visible = !isWeekMode) {
                                    HorizontalCalendar(
                                        state = monthState,
                                        dayContent = { day ->
                                            val isSelectable = day.position == DayPosition.MonthDate
                                            Day(
                                                day.date,
                                                isSelected = isSelectable && selections.contains(day.date),
                                                isSelectable = isSelectable,
                                            ) { clicked ->
                                                if (selections.contains(clicked)) {
                                                    selections.remove(clicked)
                                                } else {
                                                    selections.add(clicked)
                                                }
                                            }
                                        },
                                    )
                                }
                                AnimatedVisibility(visible = isWeekMode) {
                                    WeekCalendar(
                                        state = weekState,
                                        dayContent = { day ->
                                            val isSelectable = day.position == WeekDayPosition.RangeDate
                                            Day(
                                                day.date,
                                                isSelected = isSelectable && selections.contains(day.date),
                                                isSelectable = isSelectable,
                                            ) { clicked ->
                                                if (selections.contains(clicked)) {
                                                    selections.remove(clicked)
                                                } else {
                                                    selections.add(clicked)
                                                }
                                            }
                                        },
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                WeekModeToggle(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    isWeekMode = isWeekMode,
                                ) { weekMode ->
                                    isAnimating = true
                                    isWeekMode = weekMode
                                    coroutineScope.launch {
                                        if (weekMode) {
                                            val targetDate = monthState.firstVisibleMonth.weekDays.last().last().date
                                            weekState.scrollToWeek(targetDate)
                                            weekState.animateScrollToWeek(targetDate) // Trigger a layout pass for title update
                                        } else {
                                            val targetMonth = weekState.firstVisibleWeek.days.first().date.yearMonth
                                            monthState.scrollToMonth(targetMonth)
                                            monthState.animateScrollToMonth(targetMonth) // Trigger a layout pass for title update
                                        }
                                        isAnimating = false
                                    }
                                }
                            }
                        }
                    }
                }
                // Review Box
                Box(modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .fillMaxWidth()
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
                ){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(animationSpec = tween(durationMillis = 300))
                    ) {
                        // Child views.
                        if(startReview){
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFACCBE1), RoundedCornerShape(8.dp))
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .clickable { startReview = false },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "뒤로 가기", fontSize = 18.sp, color = Color.Black)
                            }
                        }
                        else{
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
                                    .clickable { startReview = true }
                            ){
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
                            }
                        }
                        AnimatedVisibility(visible = startReview) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                                horizontalAlignment = Alignment.Start,
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

                            ) {
                                // Child views.
                                ReviewContent()
                                ReviewContent(true)
                                ReviewContent(true)
                                ReviewContent(true)
                                ReviewContent(true)
                            }
                        }
                    }

                }
            }

        }
    }

}
@Composable
fun ReviewContent(isChecked : Boolean = false){
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Child views.
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
            ) {
                // Child views.
                Text(
                    text = "#index",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                        color = Color(0xFF707070),
                    )
                )
                Text(
                    text = "#채팅방 이름",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                        color = Color(0xFF707070),
                    )
                )
            }
            Text(
                text = "#시간 정보",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                    color = Color(0xFF707070),
                )
            )
        }
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Child views.
            if(isChecked) {
                Text(
                    text = "원문 내용",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                        color = Color(0xFF000000),
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            } else {
                Text(
                    text = "원문 내용",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                        color = Color(0xFF000000),
                    )
                )
            }
        }
    }

}

@Composable
fun ItemRow(text : String, progress : String){
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
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
                // Percentage
                Text(
                    text = progress,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nanumsquareroundb)),
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
}

//private val dateFormatter = DateTimeFormatter.ofPattern("dd")

@Composable
fun Day(
    day: LocalDate,
    isSelected: Boolean,
    isSelectable: Boolean,
    onClick: (LocalDate) -> Unit,
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f) // This is important for square-sizing!
            .padding(6.dp)
            .clip(CircleShape)
            .background(color = if (isSelected) Color(0xFF456268) else Color.Transparent)
            .clickable(
                enabled = isSelectable,
                onClick = { onClick(day) },
            ),
        contentAlignment = Alignment.Center,
    ) {
        val textColor = when {
            isSelected -> Color.White
            isSelectable -> Color.Unspecified
            else -> Color(0xFF456268)
        }
        Text(
            text = day.dayOfMonth.toString(),
            color = textColor,
            fontSize = 14.sp,
        )
    }
}

@Composable
fun WeekModeToggle(
    modifier: Modifier,
    isWeekMode: Boolean,
    weekModeToggled: (isWeekMode: Boolean) -> Unit,
) {
    // We want the entire content to be clickable, not just the checkbox.
    Row(
        modifier = modifier
            .padding(10.dp)
            .clip(MaterialTheme.shapes.small)
            .clickable { weekModeToggled(!isWeekMode) }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
    ) {
        Checkbox(
            checked = isWeekMode,
            onCheckedChange = null, // Check is handled by parent.
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF456268)),
        )
        Text(text = "주간 모드", color = Color(0xFF456268))
    }
}

@Composable
fun CalendarHeader(daysOfWeek: List<DayOfWeek>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                text = dayOfWeek.name.take(3),
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    HomeScreen()
//}