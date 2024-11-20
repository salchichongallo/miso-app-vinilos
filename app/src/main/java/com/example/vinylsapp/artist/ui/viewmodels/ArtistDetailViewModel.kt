package com.example.vinylsapp.artist.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.IArtistRepository
import kotlinx.coroutines.launch

class ArtistDetailViewModel(
    private val artistId: Int,
    private val artistRepo: IArtistRepository,
) :
    ViewModel() {

    var artist by mutableStateOf<Artist?>(null)

    init {
        viewModelScope.launch {
            artistRepo.getBy(artistId).collect {
                artist = it
            }
        }
    }
}
