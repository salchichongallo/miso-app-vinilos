package com.example.vinylsapp.album.tracks.mock

import com.example.vinylsapp.album.tracks.models.Track
import com.example.vinylsapp.album.tracks.models.TrackNew
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository

class TrackRepositoryMock(private val tracks: MutableList<Track>) : ITrackRepository {
    override suspend fun getAll(albumId: Int): List<Track> {
        return tracks
    }

    override suspend fun create(trackNew: TrackNew, albumId: Int) {
        val newTrackBody = TrackNew(
            name = trackNew.name,
            duration = trackNew.duration
        )
        val newTrack = Track(
            id = 1,
            name = newTrackBody.name,
            duration = newTrackBody.duration
        )
        tracks.add(newTrack)
    }
}
