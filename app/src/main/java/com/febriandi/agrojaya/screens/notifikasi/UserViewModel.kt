package com.febriandi.agrojaya.screens.notifikasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febriandi.agrojaya.data.Repository.UserRepository
import com.febriandi.agrojaya.data.firebase.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _tokenUpdateState = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val tokenUpdateState: StateFlow<Resource<Unit>> = _tokenUpdateState

    fun updateFCMToken(token: String) {
        val currentUser = firebaseAuth.currentUser
        currentUser?.let { user ->
            viewModelScope.launch {
                _tokenUpdateState.value = Resource.Loading()
                val result = userRepository.updateFCMToken(user.uid, token)
                _tokenUpdateState.value = result
            }
        }
    }
}