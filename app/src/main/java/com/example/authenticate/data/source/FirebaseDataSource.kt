package com.example.authenticate.data.source

import com.google.firebase.auth.FirebaseAuth

class FirebaseDataSource {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun getFirebaseAuth(): FirebaseAuth = firebaseAuth
}