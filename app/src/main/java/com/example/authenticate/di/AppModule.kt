package com.example.authenticate.di

import com.example.authenticate.data.source.FirebaseDataSource
import com.example.authenticate.ui.login.LoginViewModel
import com.example.authenticate.ui.signup.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Provide FirebaseAuth instance
    single { FirebaseDataSource().getFirebaseAuth() }

    // Provide ViewModels
    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
}