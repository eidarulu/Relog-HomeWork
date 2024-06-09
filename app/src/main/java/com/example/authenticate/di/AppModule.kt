package com.example.authenticate.di

import com.example.authenticate.data.repository.AuthRepository
import com.example.authenticate.data.repository.AuthRepositoryImpl
import com.example.authenticate.data.source.remote.FirebaseDataSource
import com.example.authenticate.viewmodel.LoginViewModel
import com.example.authenticate.viewmodel.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseDataSource().getFirebaseAuth() }
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
}