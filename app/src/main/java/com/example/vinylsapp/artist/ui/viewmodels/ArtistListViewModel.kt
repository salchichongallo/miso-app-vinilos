package com.example.vinylsapp.artist.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.IArtistRepository
import kotlinx.coroutines.launch

class ArtistListViewModel(private val artistRepo: IArtistRepository) : ViewModel() {
    var artists by mutableStateOf(listOf<Artist>())
    var loading by mutableStateOf(false)

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() {
        loading = true
        try {
            artists = artistRepo.getAll()
        } catch (error: Exception) {
            artists = listOf()
        } finally {
            loading = false
        }
    }
}
