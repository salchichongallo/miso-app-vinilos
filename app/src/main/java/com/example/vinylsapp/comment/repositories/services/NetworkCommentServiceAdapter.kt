package com.example.vinylsapp.comment.repositories.services

import com.example.vinylsapp.comment.models.Comment
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkCommentServiceAdapter {
    @GET("comments")
    suspend fun fetchComments(albumId: Int): List<Comment>

    @POST("comments")
    suspend fun createComment(rating: Int, description: String, albumId: Int): Comment
}