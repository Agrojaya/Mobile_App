package com.febriandi.agrojaya.screens.login
import com.google.firebase.auth.FirebaseUser

data class LoginGoogleState(
    val success: FirebaseUser? = null,
    val error: String? = "",
    val loading: Boolean = true
)
data class LoginState(
    val success: String? = "",
    val error: String? = "",
    val loading: Boolean = false
)