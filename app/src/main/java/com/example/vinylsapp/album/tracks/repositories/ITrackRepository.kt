package com.example.vinylsapp.album.tracks.repositories

import com.example.vinylsapp.album.tracks.models.Track
import com.example.vinylsapp.album.tracks.models.TrackNew

interface ITrackRepository {
    suspend fun getAll(albumId: Int): List<Track>
    suspend fun create(trackNew: TrackNew, albumId: Int): Unit
}
