package com.example.vinylsapp.artist.repositories.services

import com.example.vinylsapp.artist.models.Artist
import retrofit2.http.GET

interface NetworkArtistServiceAdapter {
    @GET("musicians")
    suspend fun fetchArtists(): List<Artist>
}
