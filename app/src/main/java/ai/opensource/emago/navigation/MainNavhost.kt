package ai.opensource.emago.navigation

import ai.opensource.emago.screens.chat.ChatMainScreen
import ai.opensource.emago.screens.home.HomeMainScreen
import ai.opensource.emago.screens.profile.ProfileScreen
import ai.opensource.emago.viewmodels.ChatViewModel
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = "home"){
        composable("chat"){ ChatMainScreen(navController) }
        composable("home"){ HomeMainScreen(navController) }
        composable("profile"){ ProfileScreen(navController) }
    }
}