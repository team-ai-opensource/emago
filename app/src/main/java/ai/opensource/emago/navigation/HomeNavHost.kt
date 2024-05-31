package ai.opensource.emago.navigation

import ai.opensource.emago.screens.home.HomeScreen
import ai.opensource.emago.screens.home.ReviewScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.time.LocalDate

@Composable
fun HomeNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable(
            "review/{selectedDate}",
            arguments = listOf(navArgument("selectedDate") { type = NavType.StringType })
        ) { backStackEntry ->
            val date = LocalDate.parse(backStackEntry.arguments?.getString("selectedDate"))
            ReviewScreen(date)
        }
        //Statistics Screen will be added here
        // composable("statistics") { StatisticsScreen() }
    }
}