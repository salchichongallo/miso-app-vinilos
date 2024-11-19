package com.example.vinylsapp.album.repositories.services

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumNew
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkAlbumServiceAdapter {
    @GET("albums")
    suspend fun fetchAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun fetchAlbum(@Path("id") id: Int): Album

    @POST("albums")
    suspend fun addAlbum(@Body albumNew: AlbumNew): Album
}