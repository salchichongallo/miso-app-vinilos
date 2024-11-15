package com.example.vinylsapp.comment.repositories

import com.example.vinylsapp.comment.models.Comment

interface ICommentRepository {
    suspend fun getAll(albumId: Int): List<Comment>
    suspend fun create(rating: Int, description: String, albumId: Int): Comment
}