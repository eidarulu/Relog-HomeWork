package com.example.authenticate.ui.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.authenticate.viewmodel.SignupViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SignupScreen(navController: NavController) {
    val signupViewModel: SignupViewModel = getViewModel()
    val signupState by signupViewModel.signupState.collectAsState()

    // UI code and handling based on signupState
}