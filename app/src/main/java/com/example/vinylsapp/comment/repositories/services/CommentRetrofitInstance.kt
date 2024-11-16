package com.example.vinylsapp.comment.repositories.services

import com.example.vinylsapp.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CommentRetrofitInstance {
    fun makeCommentService(): NetworkCommentServiceAdapter {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkCommentServiceAdapter::class.java)
    }
}