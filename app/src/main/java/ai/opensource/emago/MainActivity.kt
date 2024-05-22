package ai.opensource.emago

import ai.opensource.emago.Screens.ChatListScreen
import ai.opensource.emago.Screens.LoginScreen
import ai.opensource.emago.Screens.SignUpScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ai.opensource.emago.ui.theme.EmagoTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


sealed class DestinationScreen(var route : String) {
    object SignUp: DestinationScreen("signup")
    object Login: DestinationScreen("login")
    object Profile: DestinationScreen("profile")
    object ChatList: DestinationScreen("chatList")
    object SingleChat: DestinationScreen("singleChat/{chatId}") {
        fun createRoute(id: String) = "singleChat/$id"
    }
}


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
                    EmagoAppNavigation()
                }
            }
        }
    }
    @Composable
    fun EmagoAppNavigation() {

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = DestinationScreen.SignUp.route) {
            composable(DestinationScreen.SignUp.route) {
                SignUpScreen(navController)
            }
            composable(DestinationScreen.Login.route) {
                LoginScreen(navController)
            }
            composable(DestinationScreen.Profile.route) {
                LoginScreen(navController)
            }
            composable(DestinationScreen.ChatList.route) {
                ChatListScreen(navController)
            }
        }

    }
}

@Composable
fun ShowVectorDrawable(id: Int) {
    // 'your_vector_drawable'는 res/drawable 폴더에 있는 XML 파일의 이름입니다.
    val image = painterResource(id = id)
    Image(painter = image, contentDescription = "Vector Drawable", modifier = Modifier
        .padding(8.dp)
        .width(48.dp)
        .height(48.dp)
        .clip(CircleShape)
        .background(color = Color(0xFFD0E8F2)))
}

@Composable
fun ShowVectorDrawable2(id: Int) {
    // 'your_vector_drawable'는 res/drawable 폴더에 있는 XML 파일의 이름입니다.
    val image = painterResource(id = id)
    Image(painter = image, contentDescription = "Vector Drawable", modifier = Modifier)
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

