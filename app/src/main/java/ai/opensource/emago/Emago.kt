package ai.opensource.emago

import ai.opensource.emago.screens.MainScreen
import ai.opensource.emago.screens.sign.LogInScreen
import ai.opensource.emago.viewmodels.AuthViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Emago(){
    val navController = rememberNavController()
    val avm = hiltViewModel<AuthViewModel>()
    val isLoggedIn by avm.isLoggedIn.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "main" else "logIn"
    ) {
        composable("logIn") { LogInScreen(navController, avm) }
        composable("main") { MainScreen() }
    }
}