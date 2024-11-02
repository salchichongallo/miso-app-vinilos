package com.example.vinylsapp.album.tracks.repositories.services

import com.example.vinylsapp.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TrackRetrofitInstance {
    fun makeTrackService(): NetworkTrackServiceAdapter {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkTrackServiceAdapter::class.java)
    }
}
