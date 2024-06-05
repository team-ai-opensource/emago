package ai.opensource.emago

import ai.opensource.emago.screens.chat.ChatCreateScreen
import ai.opensource.emago.screens.chat.ChatListScreen
import ai.opensource.emago.screens.home.HomeScreen
import ai.opensource.emago.screens.home.ReviewScreen
import ai.opensource.emago.screens.profile.ProfileScreen
import ai.opensource.emago.screens.profile.ProfileSettingScreen
import ai.opensource.emago.screens.sign.FirstScreen
import ai.opensource.emago.screens.sign.SignUpScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Emago() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { FirstScreen(navController) }
        composable("signUp") { SignUpScreen(navController) }

        composable("home") {
            Layout(navController) {
                HomeScreen(navController)
            }
        }
        composable("chatList") {
            Layout(navController) {
                ChatListScreen(navController)
            }
        }
        composable("profile") {
            Layout(navController) {
                ProfileScreen(navController)
            }
        }

        composable("review") { ReviewScreen() }
        composable("profileSet") { ProfileSettingScreen() }
        composable("chatCreate") { ChatCreateScreen(navController) }

    }
}

@Composable
fun Layout(navController: NavController, screen: @Composable () -> Unit) {
    Scaffold(
        bottomBar = { MainBottomBar(navController) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            screen()
        }
    }
}

@Composable
fun MainBottomBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Chat") },
            label = { Text("Chat") },
            selected = navController.currentBackStackEntry?.destination?.route == "chat",
            onClick = { navController.navigate("chat") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = navController.currentBackStackEntry?.destination?.route == "home",
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = navController.currentBackStackEntry?.destination?.route == "profile",
            onClick = { navController.navigate("profile") }
        )
    }
}
