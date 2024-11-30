package com.example.vinylsapp.artist.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.artist.repositories.IArtistRepository
import kotlinx.coroutines.launch

class ArtistListViewModel(private val artistRepo: IArtistRepository) : ViewModel() {
    var artists = artistRepo.getAll()
    var loading by mutableStateOf(false)
    var hasError by mutableStateOf(false)

    init {
        loadArtists()
    }

    fun loadArtists() {
        viewModelScope.launch {
            loading = true
            try {
                artistRepo.fetchAll()
                hasError = false
            } catch (error: Exception) {
                Log.e("artist", "Could not fetch artists", error)
                hasError = true
            } finally {
                loading = false
            }
        }
    }
}
