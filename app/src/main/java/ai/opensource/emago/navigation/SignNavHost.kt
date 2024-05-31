package ai.opensource.emago.navigation

import ai.opensource.emago.screens.MainScreen
import ai.opensource.emago.screens.home.HomeMainScreen
import ai.opensource.emago.screens.home.ReviewScreen
import ai.opensource.emago.screens.sign.LogInScreen
import ai.opensource.emago.screens.sign.SignUpScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SignNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "logIn"){
        composable("logIn"){ LogInScreen(navController) }
        composable("signUp") { SignUpScreen(navController) }
        composable("homeMain"){ HomeMainScreen(navController)}  // Temp route to Find PW Btn
        composable("Main"){ MainScreen()}
    }
}