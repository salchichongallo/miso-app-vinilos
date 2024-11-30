package com.example.vinylsapp.album.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.repositories.IAlbumRepository
import kotlinx.coroutines.launch

class AlbumListViewModel(private val albumRepo: IAlbumRepository) : ViewModel() {
    val albums = albumRepo.getAll()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                albumRepo.fetchAll()
            } catch (error: Exception) {
                Log.e("album", "Could not fetch albums", error)
            }
        }
    }
}
