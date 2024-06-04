package ai.opensource.emago.screens.chat

import ai.opensource.emago.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatRoomScreen() {
    Scaffold(
        topBar = {},
        bottomBar = {},
    ) { innerPadding ->
        //User View
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFE5E5E5))
        ) {
            ChatContent("ChatId", "UserId", "SYS", true, true, true)
        }
    }
}

@Composable
fun ChatContent(chatId: String,
                userId: String,
                chatType : String,
                isFirstChat : Boolean,
                isTimeChange : Boolean,
                isEMAGOReady : Boolean)
{
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ){
        when (chatType) {
            "SYS" -> {
                //System Message
                Row(
                    horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .background(color = Color(0x26000000), shape = RoundedCornerShape(size = 34.dp))
                        .padding(start = 10.dp, top = 4.dp, end = 9.dp, bottom = 4.dp)
                ) {
                    // Child views.
                    Text(
                        text = "TimeStamp",
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                            color = Color(0xFFF8F8F9),
                        )
                    )
                }
            }
            "ME" -> {
                //My Message

            }
            else -> { //chatType == "OTHER"
                //Other's Message

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ChatRoomScreenPreview() {
    ChatRoomScreen()
}