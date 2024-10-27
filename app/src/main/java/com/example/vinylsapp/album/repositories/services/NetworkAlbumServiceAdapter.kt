package com.example.vinylsapp.album.repositories.services

import com.example.vinylsapp.album.models.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkAlbumServiceAdapter {
    @GET("albums")
    suspend fun fetchAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun fetchAlbum(@Path("id") id: Int): Album
}

