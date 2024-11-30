package com.example.vinylsapp.artist.repositories.services

import com.example.vinylsapp.artist.models.Artist
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkArtistServiceAdapter {
    @GET("musicians")
    suspend fun fetchArtists(): List<Artist>

    @POST("albums/{albumId}/musicians/{artistId}")
    suspend fun addToAlbum(@Path("artistId") artistId: Int, @Path("albumId") albumId: Int)
}
