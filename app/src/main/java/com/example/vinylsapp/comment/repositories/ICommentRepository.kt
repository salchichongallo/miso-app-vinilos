package com.example.vinylsapp.comment.repositories

import com.example.vinylsapp.comment.models.Comment
import kotlinx.coroutines.flow.StateFlow

interface ICommentRepository {
    suspend fun getAll(albumId: Int): StateFlow<List<Comment>>
    suspend fun create(rating: Int, description: String, albumId: Int)
    suspend fun fetchAll(albumId: Int)
}