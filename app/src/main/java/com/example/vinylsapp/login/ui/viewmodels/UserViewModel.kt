package com.example.vinylsapp.login.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.login.models.Role
import com.example.vinylsapp.login.repositories.IUserRepository
import kotlinx.coroutines.launch

class UserViewModel(val userRepo: IUserRepository) : ViewModel() {
    var isCollector by mutableStateOf(false)

    init {
        viewModelScope.launch {
            userRepo.getUser().collect {
                isCollector = it.isCollector
            }
        }
    }

    fun signIn(role: Role) {
        userRepo.signIn(role)
    }
}
