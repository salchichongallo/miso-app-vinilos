package com.example.vinylsapp.album.mocks

import com.example.vinylsapp.album.tracks.models.Track
import com.example.vinylsapp.album.tracks.models.TrackNew
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository

class EmptyTrackRepository : ITrackRepository {
    override suspend fun getAll(albumId: Int): List<Track> {
        return listOf()
    }

    override suspend fun create(trackNew: TrackNew, albumId: Int) {
        // ...
    }
}
