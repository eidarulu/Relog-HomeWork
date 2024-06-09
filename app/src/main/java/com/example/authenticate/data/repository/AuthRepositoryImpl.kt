package com.example.authenticate.data.repository

import com.example.authenticate.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun login(email: String, password: String): Flow<Result<User>> = flow {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = firebaseAuth.currentUser
            user?.let {
                emit(Result.success(User(it.displayName ?: "", it.email ?: "")))
            } ?: throw Exception("User not found")
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun signup(name: String, email: String, password: String): Flow<Result<User>> = flow {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = firebaseAuth.currentUser
            user?.let {
                it.updateProfile(userProfileChangeRequest { displayName = name }).await()
                emit(Result.success(User(name, it.email ?: "")))
            } ?: throw Exception("User not found")
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}