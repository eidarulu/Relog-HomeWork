package com.example.authenticate.data


// This is a generic class that represents a one-time event.
// It's used to handle events that should only be handled once, such as showing a Snackbar, navigation events, etc.
open class Event<out T> (private val content: T) {
    // This property is used to prevent the same event from being handled multiple times.
    // A flag indicating whether the event has been handled.
    // It's initially set to false.
    var hasBeenHandled = false
        private set

    // This method is used to retrieve the content of the event.
    // If the event has not been handled (`hasBeenHandled` is `false`), it sets `hasBeenHandled` to `true` and returns the content.
    // If the event has already been handled, it returns `null`.
    // This ensures that the content can only be consumed once.
    fun getContentOrNull(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}