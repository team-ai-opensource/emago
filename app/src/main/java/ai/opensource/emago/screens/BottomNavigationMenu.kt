package ai.opensource.emago.screens

import ai.opensource.emago.DestinationScreen
import ai.opensource.emago.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigationMenu(navController: NavController) {
    Box(){
        NavigationBar(
            containerColor = Color(0x00000000),
            contentColor = Color(0x00000000),
        ) {
            NavigationBarItem(

                icon = {
                    Image(
                        painter = painterResource(id = R.drawable._msg_icon),
                        contentDescription = "msg",
                        contentScale = ContentScale.None
                    )
                },
                selected = false,
                onClick = { navController.navigate(DestinationScreen.ChatList.route)}

            )
            NavigationBarItem(
                icon = { Image(
                    painter = painterResource(id = R.drawable.main_icon_black),
                    contentDescription = "msg",
                    contentScale = ContentScale.None
                ) },
                selected = false,
                onClick = { navController.navigate(DestinationScreen.Home.route)}
            )
            NavigationBarItem(
                icon = { Image(
                    painter = painterResource(id = R.drawable._profile_icon),
                    contentDescription = "msg",
                    contentScale = ContentScale.None
                ) },
                selected = false,
                onClick = { navController.navigate(DestinationScreen.Profile.route)}
            )
        }
    }
}