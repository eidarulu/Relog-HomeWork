package com.example.authenticate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authenticate.data.model.User
import com.example.authenticate.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SignupViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _signupState = MutableSharedFlow<Result<User>>()
    val signupState: SharedFlow<Result<User>> get() = _signupState

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.signup(name, email, password).collect {
                _signupState.emit(it)
            }
        }
    }
}