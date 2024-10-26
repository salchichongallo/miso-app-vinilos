package com.example.vinylsapp.album.repositories.services

import com.example.vinylsapp.album.models.Album
import retrofit2.http.GET

interface NetworkAlbumServiceAdapter {
    @GET("albums")
    suspend fun fetchAlbums(): List<Album>
}