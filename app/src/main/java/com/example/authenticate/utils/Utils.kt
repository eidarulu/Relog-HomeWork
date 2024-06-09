package com.example.authenticate.utils

import com.google.firebase.auth.FirebaseAuthException

fun getFirebaseErrorMessage(exception: FirebaseAuthException): String {
    return when (exception.errorCode) {
        "ERROR_INVALID_EMAIL" -> "The email address is badly formatted."
        "ERROR_WRONG_PASSWORD" -> "The password is incorrect."
        "ERROR_USER_NOT_FOUND" -> "No user corresponding to this email."
        "ERROR_USER_DISABLED" -> "The user account has been disabled."
        "ERROR_EMAIL_ALREADY_IN_USE" -> "The email address is already in use by another account."
        else -> "Authentication failed."
    }
}