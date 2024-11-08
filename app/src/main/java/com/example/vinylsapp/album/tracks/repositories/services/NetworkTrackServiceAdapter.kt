package com.example.vinylsapp.album.tracks.repositories.services

import com.example.vinylsapp.album.tracks.models.Track
import com.example.vinylsapp.album.tracks.models.TrackNew
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkTrackServiceAdapter {
    @GET("albums/{albumId}/tracks")
    suspend fun fetchTracks(@Path("albumId") albumId: Int): List<Track>

    @POST("albums/{albumId}/tracks")
    suspend fun createTrack(trackNew: TrackNew, albumId: Int): Unit
}
