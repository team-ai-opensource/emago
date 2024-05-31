package ai.opensource.emago.screens

import ai.opensource.emago.CheckSignedIn
import ai.opensource.emago.CommonProgressBar
import ai.opensource.emago.DestinationScreen
import ai.opensource.emago.EMAGOViewModel
import ai.opensource.emago.R
import ai.opensource.emago.ShowVectorDrawable
import ai.opensource.emago.ShowVectorDrawable2
import ai.opensource.emago.navigateTo
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController, vm: EMAGOViewModel) {
    CheckSignedIn(vm, navController)


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val emailState = remember {
                mutableStateOf(TextFieldValue()) // 괄호넣기
            }
            val passwordState = remember {
                mutableStateOf(TextFieldValue())
            }
            val focus = LocalFocusManager.current

            Image(
                painter = painterResource(id = R.drawable.chatbot1),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Sign In",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                },
                label = { Text(text = "Email") },
                modifier = Modifier.padding(8.dp)
            )
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                },
                label = { Text(text = "Password") },
                modifier = Modifier.padding(8.dp)
            )
            Button(
                onClick = {
                    vm.loginIn(emailState.value.text, passwordState.value.text)
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "SIGN IN")
            }

            Text(text = "New User ? Go to Sign Up - >",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, DestinationScreen.SignUp.route)
                    }
            )
        }
    }
    if (vm.inProcess.value) {
        CommonProgressBar()
    }
}