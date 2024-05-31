package ai.opensource.emago.screens.sign


import ai.opensource.emago.R
import ai.opensource.emago.utils.OutlinedTextFieldBackground
import ai.opensource.emago.viewmodels.AuthViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {
    val inProcess by authViewModel.inProcess.collectAsState()
    val errorMessage by authViewModel.errorMessage.collectAsState()
    //User View
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFCF8EC))
            .verticalScroll(rememberScrollState())
    ) {
        // Body
        Column(
            verticalArrangement = Arrangement.spacedBy(29.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(start = 30.dp, top = 16.dp, end = 30.dp, bottom = 16.dp)
                .fillMaxSize()
        ) {
            // Text
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
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
            // Input Field
            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Bottom),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                var emailState by remember { mutableStateOf("") }
                var nameState by remember { mutableStateOf("") }
                var phoneState by remember { mutableStateOf("") }
                var pwState by remember { mutableStateOf("") }
                var pwckState by remember { mutableStateOf("") }


                // Text
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                ) {
                    Text(
                        text = "회원가입",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundeb)),
                            color = Color(0xFF456268),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
                OutlinedTextFieldBackground(color = Color(0x33000000)) {
                    OutlinedTextField(
                        label = { Text("Email") },
                        value = emailState,
                        onValueChange = { emailState = it },
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
                        label = { Text("이름") },
                        value = nameState,
                        onValueChange = { nameState = it },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                            color = Color(0xFF456268),
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                OutlinedTextFieldBackground(color = Color(0x33000000)) {
                    OutlinedTextField(
                        label = { Text("전화번호") },
                        value = phoneState,
                        onValueChange = { phoneState = it },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                            color = Color(0xFF456268),
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                OutlinedTextFieldBackground(color = Color(0x33000000)) {
                    OutlinedTextField(
                        label = { Text("Password") },
                        value = pwState,
                        onValueChange = { pwState = it },
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
                OutlinedTextFieldBackground(color = Color(0x33000000)) {
                    OutlinedTextField(
                        label = { Text("Password") },
                        value = pwckState,
                        onValueChange = { pwckState = it },
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
                // Sign Up Button
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        authViewModel.signUp(
                            email = emailState,
                            name = nameState,
                            number = phoneState,
                            password = pwState
                        )
                    },
                    enabled = !inProcess,
                    shape = RoundedCornerShape(size = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF79A3B1),
                    )
                ) {
                    if (inProcess) {
                        CircularProgressIndicator()
                    } else {
                        Text(
                            text = "회원가입",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                                color = Color(0xFFFFFFFF)
                            ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                errorMessage?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(it, color = MaterialTheme.colorScheme.error)
                }

                // Back to Sign In
                TextButton(onClick = {
                    navController.navigate("logIn") {
                        popUpTo("logIn") {
                            inclusive = true
                        }
                    }
                }) {
                    Text(
                        text = "계정이 있나요? 로그인 하러 가기",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                            color = Color(0xFF456268),
                        )
                    )
                }
            }
        }
    }
}
