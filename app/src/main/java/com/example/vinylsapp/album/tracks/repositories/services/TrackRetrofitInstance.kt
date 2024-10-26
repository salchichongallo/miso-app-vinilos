package com.example.vinylsapp.album.tracks.repositories.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TrackRetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:3000/"

    fun makeTrackService(): NetworkTrackServiceAdapter {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkTrackServiceAdapter::class.java)
    }
}