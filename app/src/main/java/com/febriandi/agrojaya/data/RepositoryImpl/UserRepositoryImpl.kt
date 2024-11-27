package com.febriandi.agrojaya.data.RepositoryImpl

import com.febriandi.agrojaya.data.Repository.UserRepository
import com.febriandi.agrojaya.data.firebase.Resource
import com.febriandi.agrojaya.data.remote.ApiService
import com.febriandi.agrojaya.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {

    override suspend fun createUser(uid: String, username: String, email: String): Resource<User> {
        return try {
            val user = User(uid, username, email)
            val response = apiService.createUser(user)

            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to create user: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Error creating user: ${e.message}")
        }
    }
}