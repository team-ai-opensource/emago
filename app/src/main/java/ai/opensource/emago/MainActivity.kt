package ai.opensource.emago

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ai.opensource.emago.ui.theme.EmagoTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EmagoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginPage()
                }
            }
        }
    }
}

@Composable
fun ShowVectorDrawable(id: Int) {
    // 'your_vector_drawable'는 res/drawable 폴더에 있는 XML 파일의 이름입니다.
    val image = painterResource(id = id)
    Image(painter = image, contentDescription = "Vector Drawable")
}

@Composable
fun LoginPage() {
    

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowVectorDrawable(R.drawable.chatbot1)
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Welcome to E-mago", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Row {
            Box {
                Button(onClick = {

                }) {
                    ShowVectorDrawable(R.drawable.facebook)
                }
            }
            Box {
                Button(onClick = { /*TODO*/ },
                ) {
                    ShowVectorDrawable(R.drawable.google)

                }
            }
            Box {
                Button(onClick = { /*TODO*/ },
                ) {
                    ShowVectorDrawable(R.drawable.apple)


                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmagoTheme {
        ShowVectorDrawable(R.drawable.chatbot1)
    }
}