package com.example.authenticate.ui.login

import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.authenticate.R
import com.example.authenticate.ui.component.HaveAccountRow
import com.example.authenticate.ui.component.items.AuthButton
import com.example.authenticate.ui.component.items.AuthTextField
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = getViewModel()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf(false) }
    var errorPassword by remember { mutableStateOf(false) }

    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier =  Modifier.fillMaxSize()){
            // Background Image
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
                    text = stringResource(R.string.login),
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (errorEmail) {
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
                        errorEmail = false
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (errorPassword) {
                    Text(
                        text = "Enter Password",
                        color = Color.Red,
                        modifier = Modifier.padding(end = 100.dp)
                    )
                }
                AuthTextField(
                    hint = stringResource(id = R.string.password),
                    value = password,
                    onValueChange = {
                        password = it
                        errorPassword = false
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                AuthButton(
                    onClick = {
                        if (email.isNotEmpty()) {
                            errorEmail = false
                            if (password.isNotEmpty()) {
                                errorPassword = false
                                loginViewModel.login(email, password)
                            } else {
                                errorPassword = true
                            }
                        } else {
                            errorEmail = true
                        }
                    },
                    text = stringResource(id = R.string.login),
                )
                NotificationMessage(loginViewModel)
                Spacer(modifier = Modifier.height(8.dp))
                if (loginViewModel.signedIn.value) {
                    navController.navigate("main")
                }
                loginViewModel.signedIn.value = false

                HaveAccountRow(
                    onSignupTap = {
                        navController.navigate("signup")
                    },
                    question = stringResource(id = R.string.dont_have_account),
                    link = stringResource(id = R.string.signup)
                )
            }
        }
    }
}

@Composable
fun NotificationMessage(lvm: LoginViewModel) {
    val notifState = lvm.popupNotification.value
    val notifMessage = notifState?.getContentOrNull()
    if (notifMessage != null) {
        Toast.makeText(LocalContext.current, notifMessage, Toast.LENGTH_SHORT).show()
    }
}