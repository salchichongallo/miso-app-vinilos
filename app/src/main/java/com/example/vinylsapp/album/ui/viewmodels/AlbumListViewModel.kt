package com.example.vinylsapp.album.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vinylsapp.VynilsApplication
import com.example.vinylsapp.album.repositories.IAlbumRepository
import kotlinx.coroutines.launch

class AlbumListViewModel(private val albumRepo: IAlbumRepository, private val savedStateHandle: SavedStateHandle?) : ViewModel() {
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val dataSource = (this[APPLICATION_KEY] as VynilsApplication).dataSource
                AlbumListViewModel(
                    albumRepo = dataSource,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}
