package com.example.vinylsapp.album.tracks.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.tracks.models.Track
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository
import kotlinx.coroutines.launch

class TrackListViewModel(private val trackRepo: ITrackRepository, private val albumId: Int) :
    ViewModel() {
    var tracks by mutableStateOf(listOf<Track>())

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                tracks = trackRepo.getAll(albumId)
            } catch (e: Exception) {
                tracks = listOf()
            }
        }
    }
}
