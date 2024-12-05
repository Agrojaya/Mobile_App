package com.febriandi.agrojaya.model

import com.febriandi.agrojaya.utils.Resource

data class User(
    val uid: String,
    val username: String,
    val email: String
)

data class ProfileState(
    val profilePhotoUri: String? = null,
    val isLoading: Boolean = false
)

data class UpdateUserRequest(
    val username: String,
    val phoneNumber: String
)

data class UserResponse(
    val id: String,
    val username: String,
    val email: String,
    val phoneNumber: String
)


