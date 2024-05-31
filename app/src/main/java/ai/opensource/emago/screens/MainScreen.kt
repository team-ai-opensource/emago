package ai.opensource.emago.screens

import ai.opensource.emago.navigation.MainBottomBar
import ai.opensource.emago.navigation.MainNavHost
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MainBottomBar(navController) }
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)){
            MainNavHost(navController = navController)
        }
    }
}