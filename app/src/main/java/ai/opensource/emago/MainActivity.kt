package ai.opensource.emago


import ai.opensource.emago.screens.chat.ChatCreateScreen
import ai.opensource.emago.screens.chat.ChatListScreen
import ai.opensource.emago.screens.sign.FirstScreen
import ai.opensource.emago.screens.home.HomeScreen
import ai.opensource.emago.screens.profile.ProfileScreen
import ai.opensource.emago.screens.home.ReviewScreen
import ai.opensource.emago.screens.sign.SignUpScreen
import ai.opensource.emago.screens.chat.SingleChatScreen
import ai.opensource.emago.ui.theme.EmagoTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint


sealed class DestinationScreen(var route: String) {
    object Home : DestinationScreen("home")
    object SignUp : DestinationScreen("signup")
    object Login : DestinationScreen("login")
    object Profile : DestinationScreen("profile")
    object ChatList : DestinationScreen("chatList")
    object ChatCreate : DestinationScreen("chatCreate")
    object SingleChat : DestinationScreen("singleChat/{chatId}") {
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
            composable(DestinationScreen.Profile.route) {
                    ProfileScreen(navController, vm)
            }
            composable(DestinationScreen.ChatCreate.route) {
                ChatCreateScreen(navController)
            }
            composable(DestinationScreen.ChatList.route) {
                ChatListScreen(navController, vm)
            }

            composable(DestinationScreen.SingleChat.route) {
                val chatId = it.arguments?.getString("chatId")
                chatId?.let {
                    SingleChatScreen(navController, vm, chatId)
                }
            }

            composable("home"){ HomeScreen(navController) }
            composable("review") { ReviewScreen() }
            composable("first") { FirstScreen(navController, vm ) }
            composable("signUp") { SignUpScreen(navController, vm) }

        }
    }
}

