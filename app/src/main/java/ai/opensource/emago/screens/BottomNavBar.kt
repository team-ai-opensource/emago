package ai.opensource.emago.navigation

import ai.opensource.emago.screens.chat.ChatListScreen
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
import androidx.navigation.NavController

@Composable
fun MainBottomBar(navController: NavController) {
    NavigationBar{
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Chat") },
            label = { Text("Chat") },
            selected = navController.currentBackStackEntry?.destination?.route == "chat",
            onClick = {navController.navigate("chat")}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = navController.currentBackStackEntry?.destination?.route == "home",
            onClick = {navController.navigate("home")}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = navController.currentBackStackEntry?.destination?.route == "profile",
            onClick = {navController.navigate("profile")}
        )
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