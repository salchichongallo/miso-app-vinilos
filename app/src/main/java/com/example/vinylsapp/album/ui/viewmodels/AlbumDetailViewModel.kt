package com.example.vinylsapp.album.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.repositories.IAlbumRepository
import kotlinx.coroutines.launch

class AlbumDetailViewModel(private val albumId: Int, private val albumRepo: IAlbumRepository) :
    ViewModel() {
    var album by mutableStateOf<Album?>(null)

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            album = try {
                albumRepo.getOne(albumId)
            } catch (e: Exception) {
                null
            }
        }
    }
}
