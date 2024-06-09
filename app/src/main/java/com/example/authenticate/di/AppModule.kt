package com.example.authenticate.di

import com.example.authenticate.data.repository.AuthRepository
import com.example.authenticate.data.repository.AuthRepositoryImpl
import com.example.authenticate.data.source.remote.FirebaseDataSource
import org.koin.dsl.module

val appModule = module {
    single { FirebaseDataSource().getFirebaseAuth() }
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
}