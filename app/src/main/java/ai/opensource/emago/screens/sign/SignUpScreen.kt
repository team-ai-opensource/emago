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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {
    //
    val inProcess by authViewModel.inProcess.collectAsState()
    val errorMessage by authViewModel.errorMessage.collectAsState()

    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    if (isLoggedIn) {
        LaunchedEffect(Unit) {
            navController.navigate("Main") {
                popUpTo("logIn") { inclusive = true }
            }
        }
    }

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

                val pwMatch by remember { derivedStateOf { pwState == pwckState }}


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
                        label = { Text("이메일") },
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
                var passwordHidden by rememberSaveable { mutableStateOf(true) }
                var passwordckHidden by rememberSaveable { mutableStateOf(true) }

                OutlinedTextFieldBackground(color = Color(0x33000000)) {
                    OutlinedTextField(
                        label = { Text("비밀번호") },
                        value = pwState,
                        onValueChange = { pwState = it },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                            color = Color(0xFF456268),
                        ),
                        visualTransformation =
                        if(passwordHidden)PasswordVisualTransformation() else VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(onClick = {passwordHidden = !passwordHidden}) {
                                val visibilityIcon =
                                    if (passwordHidden) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                                // Please provide localized description for accessibility services
                                val description = if (passwordHidden) "Show password" else "Hide password"
                                Icon(imageVector = visibilityIcon, contentDescription = description)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                OutlinedTextFieldBackground(color = Color(0x33000000)) {
                    OutlinedTextField(
                        label = { Text("비밀번호 확인") },
                        value = pwckState,
                        onValueChange = { pwckState = it },
                        singleLine = true,
                        isError = !pwMatch && pwckState.isNotEmpty(),
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.nanumsquareroundr)),
                            color = Color(0xFF456268),
                        ),
                        visualTransformation =
                        if(passwordckHidden)PasswordVisualTransformation() else VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                                       IconButton(onClick = {passwordckHidden = !passwordckHidden}) {
                                           val visibilityIcon =
                                               if (passwordckHidden) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                                           // Please provide localized description for accessibility services
                                           val description = if (passwordckHidden) "Show password" else "Hide password"
                                           Icon(imageVector = visibilityIcon, contentDescription = description)
                                       }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics {
                                if (!pwMatch && pwckState.isNotEmpty())
                                    error("비밀번호가 일치하지 않습니다.")
                            }
                    )
                }
                // Sign Up Button
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {if(pwMatch){
                        authViewModel.signUp(
                            email = emailState,
                            name = nameState,
                            number = phoneState,
                            password = pwState
                        )}
                    },
                    enabled = !inProcess,
                    shape = RoundedCornerShape(size = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF79A3B1),
                    )
                ) {
                    if (inProcess) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
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
                    Spacer(modifier = Modifier.height(2.dp))
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
