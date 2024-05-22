package ai.opensource.emago.Screens

import ai.opensource.emago.DestinationScreen
import ai.opensource.emago.R
import ai.opensource.emago.ShowVectorDrawable
import ai.opensource.emago.ShowVectorDrawable2
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowVectorDrawable2(R.drawable.chatbot1)
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Welcome to E-mago", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Row {
                Box {

                    ShowVectorDrawable(R.drawable.facebook)

                }
                Box {

                    ShowVectorDrawable(R.drawable.google)


                }
                Box {

                    ShowVectorDrawable(R.drawable.apple)


                }
            }
            Text(text = "회원가입 하러가기",
                color = Color.Blue,
                modifier = Modifier.padding(8.dp).clickable {
                    navController.navigate(DestinationScreen.SignUp.route)
                }
            )
        }
    }
