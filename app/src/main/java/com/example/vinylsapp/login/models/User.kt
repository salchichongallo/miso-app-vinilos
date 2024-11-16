package com.example.vinylsapp.login.models

data class User(val role: Role) {
    val isCollector: Boolean get() = role == Role.Collector
}
