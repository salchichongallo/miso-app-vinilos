package com.example.vinylsapp.album.tracks.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.repositories.IAlbumRepository
import com.example.vinylsapp.album.tracks.models.TrackNew
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository
import kotlinx.coroutines.launch

class TrackCreateViewModel(private val albumId: Int, private val trackRepo: ITrackRepository, private val albumRepo: IAlbumRepository) : ViewModel() {
    var album by mutableStateOf<Album?>(null)
    var track by mutableStateOf(TrackNew(name = "", duration = ""))

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                album = albumRepo.getOne(albumId)
            } catch (e: Exception) {
                album = null
            }
        }
    }

    fun onTrackNameChange(newName: String) {
        track = track.copy(name = newName)
    }

    fun onTrackDurationChange(newDuration: String) {
        track = track.copy(duration = newDuration)
    }

    fun createTrack() {
        // TODO: Consumir endpoint
    }

    fun showSuccess() {
        // TODO: Make success
    }

    fun showError() {
        // TODO: Make error
    }
}