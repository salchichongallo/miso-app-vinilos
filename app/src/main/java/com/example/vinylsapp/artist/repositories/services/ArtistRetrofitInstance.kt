package com.example.vinylsapp.artist.repositories.services

import com.example.vinylsapp.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArtistRetrofitInstance {
    fun makeArtistService(): NetworkArtistServiceAdapter {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkArtistServiceAdapter::class.java)
    }
}
