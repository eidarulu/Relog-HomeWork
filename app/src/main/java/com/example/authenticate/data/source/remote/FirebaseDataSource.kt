package com.example.authenticate.data.source.remote

import com.google.firebase.auth.FirebaseAuth

class FirebaseDataSource {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun getFirebaseAuth(): FirebaseAuth = firebaseAuth
}