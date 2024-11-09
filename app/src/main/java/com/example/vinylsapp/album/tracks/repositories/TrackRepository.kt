package com.example.vinylsapp.album.tracks.repositories

import com.example.vinylsapp.album.tracks.models.Track
import com.example.vinylsapp.album.tracks.models.TrackNew
import com.example.vinylsapp.album.tracks.repositories.services.NetworkTrackServiceAdapter

class TrackRepository(private val serviceAdapter: NetworkTrackServiceAdapter) : ITrackRepository {
    override suspend fun getAll(albumId: Int): List<Track> {
        return serviceAdapter.fetchTracks(albumId)
    }

    override suspend fun create(trackNew: TrackNew, albumId: Int) {
        return serviceAdapter.createTrack(albumId, trackNew)
    }
}
