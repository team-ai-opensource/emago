package ai.opensource.emago.screens.sign

import ai.opensource.emago.navigation.SignNavHost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignScreen(signNavController: NavHostController) {
    val sigNavController = rememberNavController()
    SignNavHost(navController = sigNavController)
}