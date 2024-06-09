package com.example.authenticate.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.authenticate.viewmodel.LoginViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = getViewModel()
    val loginState by loginViewModel.loginState.collectAsState()

    // UI code and handling based on loginState
}