package com.example.vinylsapp.artist.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.IArtistRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArtistDetailViewModel(
    private val artistId: Int,
    private val artistRepo: IArtistRepository,
) :
    ViewModel() {

    var loading by mutableStateOf(false)
    var artist by mutableStateOf<Artist?>(null)

    init {
        loadArtist()
    }

    private fun loadArtist() {
        loading = true
        viewModelScope.launch {
            delay(1000) // add extra delay to avoid flash
            artistRepo.getBy(artistId).collect {
                artist = it
                loading = false
            }
        }
    }
}
