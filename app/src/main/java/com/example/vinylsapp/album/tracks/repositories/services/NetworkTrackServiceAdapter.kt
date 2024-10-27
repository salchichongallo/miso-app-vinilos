package com.example.vinylsapp.album.tracks.repositories.services

import com.example.vinylsapp.album.tracks.models.Track
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkTrackServiceAdapter {
    @GET("albums/{albumId}/tracks")
    suspend fun fetchTracks(@Path("albumId") albumId: Int): List<Track>
}
