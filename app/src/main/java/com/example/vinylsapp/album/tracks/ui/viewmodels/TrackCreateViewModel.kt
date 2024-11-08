package com.example.vinylsapp.album.tracks.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vinylsapp.album.tracks.models.TrackNew
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository

class TrackCreateViewModel(private val trackRepo: ITrackRepository, private val albumId: Int) : ViewModel() {
    var track by mutableStateOf(TrackNew(name = "", duration = ""))

    fun onTrackNameChange(newName: String) {
        track = track.copy(name = newName)
    }

    fun onTrackDurationChange(newDuration: String) {
        track = track.copy(duration = newDuration)
    }

    fun createTrack() {
        // TODO: Consumir endpoint
    }
}