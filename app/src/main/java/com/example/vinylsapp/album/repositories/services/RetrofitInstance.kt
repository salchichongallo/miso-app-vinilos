package com.example.vinylsapp.album.repositories.services

import com.example.vinylsapp.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceFactory {
    fun makeAlbumService(): NetworkAlbumServiceAdapter {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkAlbumServiceAdapter::class.java)
    }
}
