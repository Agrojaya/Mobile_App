package com.febriandi.agrojaya.data.Repository

import com.febriandi.agrojaya.data.firebase.Resource
import com.febriandi.agrojaya.model.User

interface UserRepository {
    suspend fun createUser(uid: String, username: String, email: String): Resource<User>
}