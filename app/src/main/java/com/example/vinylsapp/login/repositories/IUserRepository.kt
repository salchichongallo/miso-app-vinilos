package com.example.vinylsapp.login.repositories

import com.example.vinylsapp.login.models.Role
import com.example.vinylsapp.login.models.User
import kotlinx.coroutines.flow.StateFlow

interface IUserRepository {
    fun getUser(): StateFlow<User>
    fun signIn(role: Role)
}
