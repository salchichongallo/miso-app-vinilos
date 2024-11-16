package com.example.vinylsapp.login.repositories

import com.example.vinylsapp.login.models.Role
import com.example.vinylsapp.login.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class UserRepository : IUserRepository {
    private val user = MutableStateFlow(User(role = Role.Guest))

    override fun getUser() = user

    override fun signIn(role: Role) {
        user.update { it.copy(role = role) }
    }
}
