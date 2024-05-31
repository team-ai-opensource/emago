package ai.opensource.emago.navigation

import ai.opensource.emago.screens.home.HomeScreen
import ai.opensource.emago.screens.home.ReviewScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun HomeNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){ HomeScreen(navController) }
        composable("review") { ReviewScreen() }
        //Statistics Screen will be added here
        // composable("statistics") { StatisticsScreen() }
    }
}