package com.example.vinylsapp.album.repositories.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceFactory {
    private const val BASE_URL = "http://10.0.2.2:3000/"

    fun makeAlbumService(): NetworkAlbumServiceAdapter {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkAlbumServiceAdapter::class.java)
    }
}