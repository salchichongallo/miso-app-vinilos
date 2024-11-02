package com.example.vinylsapp.album.tracks.repositories

import com.example.vinylsapp.album.tracks.models.Track

interface ITrackRepository {
    suspend fun getAll(albumId: Int): List<Track>
}
