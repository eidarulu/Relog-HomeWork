package com.example.authenticate.data.repository

import com.example.authenticate.data.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Result<User>>
    suspend fun signup(name: String, email: String, password: String): Flow<Result<User>>
}