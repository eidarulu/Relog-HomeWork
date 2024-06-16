package com.example.authenticate.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.authenticate.data.Event
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class LoginViewModel(private val auth: FirebaseAuth) : ViewModel() {

    // Indicates whether the user is signed in
    val signedIn = mutableStateOf(false)

    // Indicates whether the login process is in progress
    private val inProgress = mutableStateOf(false)

    // Holds a one-time event for showing popup notifications
    val popupNotification = mutableStateOf<Event<String>?>(null)

    fun login(email: String, pass: String) {
        // Set the inProgress state to true, indicating that the login process has started
        inProgress.value = true

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                // This block is executed when the sign-in process completes
                if (task.isSuccessful) {
                    // If the sign-in was successful, set the signedIn state to true
                    signedIn.value = true
                    // Handle the successful sign-in event
                    handleException(null, "Login successful")
                } else {
                    // If the sign-in failed, handle the exception
                    handleException(task.exception, "Login failed")
                }
                // Set the inProgress state to false, indicating that the login process has ended
                inProgress.value = false
            }
    }

    // Handles exceptions that occur during the login process
    private fun handleException(exception: Exception? = null, customMessage: String = "") {
        // Print the stack trace of the exception, if it's not null
        exception?.printStackTrace()

        // Get the localized message of the exception, if it's not null
        val errorMsg = exception?.localizedMessage ?: ""

        // Construct the final message to be shown in the popup notification
        val message = if (customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"

        // Set the popupNotification state to a new Event with the final message
        popupNotification.value = Event(message)
    }
}