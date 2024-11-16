package com.example.vinylsapp.comment.repositories.services

import com.example.vinylsapp.comment.models.Comment
import com.example.vinylsapp.comment.repositories.models.NewComment
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkCommentServiceAdapter {
    @GET("albums/{albumId}/comments")
    suspend fun fetchComments(@Path("albumId") albumId: Int): List<Comment>

    @POST("albums/{albumId}/comments")
    suspend fun createComment(@Body() newComment: NewComment, @Path("albumId") albumId: Int): Comment
}