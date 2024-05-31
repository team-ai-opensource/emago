package ai.opensource.emago.screens.chat

import ai.opensource.emago.navigation.ChatNavHost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ChatMainScreen(mainNavController: NavHostController) {
    val chatNavController = rememberNavController()
    ChatNavHost(navController = chatNavController)
}