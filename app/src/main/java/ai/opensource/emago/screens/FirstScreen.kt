package ai.opensource.emago.screens

import ai.opensource.emago.CheckSignedIn
import ai.opensource.emago.DestinationScreen
import ai.opensource.emago.EMAGOViewModel
import ai.opensource.emago.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FirstScreen(
    navController: NavController,
    vm: EMAGOViewModel
) {
    CheckSignedIn(vm , navController )

    var isSignInState by remember { mutableStateOf(false) }
    // User View
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFCF8EC))
    ) {
        // Body
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, top = 130.dp, end = 30.dp, bottom = 40.dp)
        ) {
            // Welcome contents
            Column(
                verticalArrangement = Arrangement.spacedBy(29.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Emago Image
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
                // Welcome Text
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                ) {
                    // Text
                    Text(
                        text = "Welcome to E-mago",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundeb)),
                            color = Color(0xFF456268),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
            // Buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 30.dp)
            ) {
                AnimatedVisibility(visible = isSignInState) {
                    var value by remember { mutableStateOf("") }
                    var pw by remember { mutableStateOf("") }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedTextFieldBackground(color = Color(0x33000000)) {
                            OutlinedTextField(
                                label = { Text("Email") },
                                value = value,
                                onValueChange = { value = it },
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                    color = Color(0xFF456268),
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        OutlinedTextFieldBackground(color = Color(0x33000000)) {
                            OutlinedTextField(
                                label = { Text("Password") },
                                value = pw,
                                onValueChange = { pw = it },
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                    color = Color(0xFF456268),
                                ),
                                visualTransformation = PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
                // Sign in
                if(isSignInState){
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {navController.navigate(DestinationScreen.Home.route)/*TODO : Sign in */},
                        shape = RoundedCornerShape(size = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF79A3B1),
                        )
                    ) {
                        Text(
                            text = "로그인",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                color = Color(0xFFFFFFFF)
                            ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                } else{
                    Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { isSignInState = true },
                    shape = RoundedCornerShape(size = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF79A3B1),
                    )
                ) {
                    Text(
                        text = "로그인",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
                }
                AnimatedVisibility(visible = isSignInState) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = "비밀번호 찾기",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                color = Color(0xFF456268),
                            )
                        )
                    }
                }
                // Sign up
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { navController.navigate("signUp")},
                        shape = RoundedCornerShape(size = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF456268),
                        )
                    ) {
                        Text(
                            text = "회원가입",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                color = Color(0xFFFFFFFF),
                            ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
        }
    }
}