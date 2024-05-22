package ai.opensource.emago.Screens

import ai.opensource.emago.DestinationScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigationMenu(navController: NavController) {
    Box(){
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            NavigationBarItem(
                icon = {
                    Text(text = "채팅")
                },
                selected = true,
                onClick = { navController.navigate(DestinationScreen.ChatList.route)}
            )
            NavigationBarItem(
                icon = { Text(text = "코칭") },
                selected = true,
                onClick = { navController.navigate(DestinationScreen.Home.route)}
            )
            NavigationBarItem(
                icon = { Text(text = "프로필") },
                selected = true,
                onClick = { navController.navigate(DestinationScreen.Profile.route)}
            )
        }
    }
}