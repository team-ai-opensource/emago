package ai.opensource.emago

import ai.opensource.emago.screens.BottomNavigationMenu
import ai.opensource.emago.screens.ChatCreateScreen
import ai.opensource.emago.screens.ChatListScreen
import ai.opensource.emago.screens.FirstScreen
import ai.opensource.emago.screens.HomeScreen
import ai.opensource.emago.screens.LoginScreen
import ai.opensource.emago.screens.ProfileScreen
import ai.opensource.emago.screens.ReviewScreen
import ai.opensource.emago.screens.SignUpScreen
import ai.opensource.emago.ui.theme.EmagoTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint


sealed class DestinationScreen(var route : String) {
    object Home: DestinationScreen("home")
    object SignUp: DestinationScreen("signup")
    object Login: DestinationScreen("login")
    object Profile: DestinationScreen("profile")
    object ChatList: DestinationScreen("chatList")
    object ChatCreate: DestinationScreen("chatCreate")
    object SingleChat: DestinationScreen("singleChat/{chatId}") {
        fun createRoute(id: String) = "singleChat/$id"
    }
}

@AndroidEntryPoint
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
        var vm = hiltViewModel<EMAGOViewModel>()

        NavHost(navController = navController, startDestination = "first") {
            composable(DestinationScreen.Login.route) {
                LoginScreen(navController)
            }
            composable(DestinationScreen.Profile.route) {
                Scaffold(bottomBar = { BottomNavigationMenu(navController) }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        ProfileScreen(navController)
                    }
                }
            }
            composable(DestinationScreen.ChatCreate.route) {
                Scaffold(bottomBar = { BottomNavigationMenu(navController) }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        ChatCreateScreen(navController)
                    }
                }
            }
            composable(DestinationScreen.ChatList.route) {
                Scaffold(bottomBar = { BottomNavigationMenu(navController) }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        ChatListScreen(navController)
                    }
                }
            }
            composable(DestinationScreen.Home.route) {
                Scaffold(bottomBar = { BottomNavigationMenu(navController) }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
//                        HomeScreen()
                        HomeScreen(navController)
                    }
                }
            }
            composable("review") { ReviewScreen() }
            composable("first") { FirstScreen(navController, vm ) }
            composable("signUp") { SignUpScreen(navController, vm) }
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



