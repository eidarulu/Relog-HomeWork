package com.example.authenticate.ui.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.authenticate.ui.login.LoginViewModel

@Composable
fun NotificationMessage(lvm: LoginViewModel) {
    val notifState = lvm.popupNotification.value
    val notifMessage = notifState?.getContentOrNull()
    if (notifMessage != null) {
        Toast.makeText(LocalContext.current, notifMessage, Toast.LENGTH_SHORT).show()
    }
}