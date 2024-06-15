package com.example.authenticate.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.authenticate.R
import com.example.authenticate.ui.component.items.AuthButton
import com.example.authenticate.ui.component.items.AuthTextField
import com.example.authenticate.ui.component.HaveAccountRow
import org.koin.androidx.compose.getViewModel

@Composable
fun SignupScreen(navController: NavController) {
    val signupViewModel: SignupViewModel = getViewModel()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("")}
    var errorE by remember { mutableStateOf(false) }
    var errorP by remember { mutableStateOf(false) }
    var errorConfP by remember { mutableStateOf(false) }
    var plength by remember { mutableStateOf(false) }

    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier =  Modifier.fillMaxSize()){
            /// Background Image
            Image(painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .align(Alignment.BottomCenter)
            )
            // Content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.signup),
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                AuthTextField(
                    hint = stringResource(id = R.string.fullname),
                    value = fullName,
                    onValueChange = {
                        fullName = it
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                if (errorE) {
                    Text(
                        text = "Enter email",
                        color = Color.Red,
                        modifier = Modifier.padding(end = 100.dp)
                    )
                }
                AuthTextField(
                    hint = stringResource(id = R.string.email),
                    value = email,
                    onValueChange = {
                        email = it
                        errorE = false
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                if (errorP) {
                    Text(
                        text = "Enter Password",
                        color = Color.Red,
                        modifier = Modifier.padding(end = 100.dp)
                    )
                }
                if (plength) {
                    Text(
                        text = "Password must be at least 6 characters",
                        color = Color.Red,
                        modifier = Modifier.padding(end = 100.dp)
                    )
                }
                AuthTextField(
                    hint = stringResource(id = R.string.password),
                    value = password,
                    onValueChange = {
                        password = it
                        errorP = false
                        plength = it.length < 6
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password
                    )
                )
                AuthTextField(
                    hint = stringResource(id = R.string.confirm_password),
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        errorConfP = confirmPassword != password
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ))
                Spacer(modifier = Modifier.height(24.dp))
                AuthButton(
                    onClick = {
                        if (email.isNotEmpty()) {
                            errorE = false
                            if (password.isNotEmpty()) {
                                errorP = false
                                signupViewModel.onSignup(email, password, fullName)
                            } else {
                                errorP = true
                            }
                        } else {
                            errorE = true
                        }
                    },
                    text = stringResource(id = R.string.signup),
                )

                if (signupViewModel.signedIn.value) {
                    navController.navigate("login")
                }
                signupViewModel.signedIn.value = false

                HaveAccountRow(
                    onSignupTap = {
                        navController.navigate("login")
                    },
                    question = stringResource(id = R.string.already_have_an_account),
                    link = stringResource(id = R.string.login)
                )
            }
        }
    }
}