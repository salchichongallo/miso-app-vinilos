package com.example.vinylsapp.comment.repositories.models

data class NewComment(
    val rating: Int,
    val description: String,
    val collector: Int
)
