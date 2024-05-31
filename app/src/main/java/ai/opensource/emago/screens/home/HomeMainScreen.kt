package ai.opensource.emago.screens.home

import ai.opensource.emago.navigation.HomeNavHost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeMainScreen(mainNavController: NavHostController) {
    val homeNavController = rememberNavController()
    HomeNavHost(navController = homeNavController)
}