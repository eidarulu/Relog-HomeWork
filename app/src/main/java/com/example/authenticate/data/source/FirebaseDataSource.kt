package com.example.authenticate.data.source

import com.google.firebase.auth.FirebaseAuth

// This class is a data source for Firebase Authentication.
// It's used to interact with Firebase Auth services.
class FirebaseDataSource {
    // FirebaseAuth instance is created here.
    // FirebaseAuth is the entry point of the Firebase Authentication SDK.
    private val firebaseAuth = FirebaseAuth.getInstance()

    // This function returns the FirebaseAuth instance.
    // It's used to perform various Firebase Authentication operations like sign-in, sign-up etc.
    fun getFirebaseAuth(): FirebaseAuth = firebaseAuth
}