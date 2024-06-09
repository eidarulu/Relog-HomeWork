package com.example.authenticate.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.authenticate.data.Event
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(private val auth: FirebaseAuth) : ViewModel() {

    val signedIn = mutableStateOf(false)
    private val inProgress = mutableStateOf(false)
    private val popupNotification = mutableStateOf<Event<String>?>(null)

    fun login(email: String, pass: String) {
        inProgress.value = true

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signedIn.value = true
                    handleException(null, "Login successful")
                } else {
                    handleException(task.exception, "Login failed")
                }
                inProgress.value = false
            }
    }

    private fun handleException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value = Event(message)
    }
}