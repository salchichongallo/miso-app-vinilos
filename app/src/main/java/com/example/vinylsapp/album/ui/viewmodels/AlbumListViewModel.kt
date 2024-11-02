package com.example.vinylsapp.album.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.repositories.IAlbumRepository
import kotlinx.coroutines.launch

class AlbumListViewModel(private val albumRepo: IAlbumRepository) : ViewModel() {
    var albums by mutableStateOf(listOf<Album>())

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                albums = albumRepo.getAll()
            } catch (e: Exception) {
                albums = listOf()
            }
        }
    }
}
