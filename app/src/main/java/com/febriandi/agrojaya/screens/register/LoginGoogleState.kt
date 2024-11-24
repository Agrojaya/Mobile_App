package com.febriandi.agrojaya.screens.register

import com.google.firebase.auth.FirebaseUser

data class LoginGoogleState(
    val success: FirebaseUser? = null,
    val error: String? = "",
    val loading: Boolean = true
)
