package com.example.authenticate.di

import com.example.authenticate.data.source.FirebaseDataSource
import com.example.authenticate.ui.login.LoginViewModel
import com.example.authenticate.ui.signup.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// This is the Koin module for your app.
// Koin is a lightweight dependency injection framework for Kotlin.
// It helps you to manage your dependencies in a clean and easy way.
val appModule = module {
    // Provide FirebaseAuth instance
    // The 'single' function ensures that a single instance of FirebaseAuth is created and used throughout the app.
    // FirebaseDataSource().getFirebaseAuth() is the constructor call for FirebaseAuth.
    single { FirebaseDataSource().getFirebaseAuth() }

    // Provide ViewModels
    // The 'viewModel' function is used to declare a ViewModel.
    // The 'get()' function is used to retrieve the FirebaseAuth instance that we declared above.
    // This instance is passed to the ViewModel constructors.
    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
}