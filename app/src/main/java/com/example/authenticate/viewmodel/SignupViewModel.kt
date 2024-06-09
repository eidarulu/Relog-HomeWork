package com.example.authenticate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authenticate.data.model.User
import com.example.authenticate.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignupViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _signupState = MutableStateFlow<Result<User>?>(null)
    val signupState: StateFlow<Result<User>?> = _signupState.asStateFlow()

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.signup(name, email, password).collect { result ->
                _signupState.value = result
            }
        }
    }

    fun resetState() {
        _signupState.value = null
    }
}