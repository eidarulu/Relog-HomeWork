package com.example.authenticate.di

import com.example.authenticate.data.repository.AuthRepository
import com.example.authenticate.data.repository.AuthRepositoryImpl
import com.example.authenticate.data.source.remote.FirebaseDataSource
import com.example.authenticate.viewmodel.LoginViewModel
import com.example.authenticate.viewmodel.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Provide FirebaseAuth instance
    single { FirebaseDataSource().getFirebaseAuth() }

    // Provide AuthRepository implementation
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    // Provide ViewModels
    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
}