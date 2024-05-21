package ai.opensource.emago.Screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ai.opensource.emago.DestinationScreen

@Composable
fun SignUpScreen( navController: NavController) {
    Text(text = "Navigate to Other Screen", modifier = Modifier.clickable {
        navController.navigate(DestinationScreen.Login.route)
    })
}