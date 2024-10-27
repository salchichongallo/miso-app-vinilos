package com.example.vinylsapp.album.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.repositories.AlbumRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AlbumListViewModel(private val albumRepo: AlbumRepository) : ViewModel() {
    var albums by mutableStateOf(listOf<Album>())

    private var fetchJob: Job? = null

    init {
        load()
    }

    private fun load() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                albums = albumRepo.getAll()
            } catch (e: Exception) {
                albums = listOf()
            }
        }
    }
}
