package com.example.vinylsapp.artist.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.artist.repositories.IArtistRepository
import kotlinx.coroutines.launch

class ArtistAlbumViewModel(
    private val artistRepo: IArtistRepository,
    private val artistId: Int,

) : ViewModel() {

    var selectedAlbum: LiveData<Album>? = null
    var artistName by mutableStateOf("")

    init {
        loadArtistById()
    }

    private fun loadArtistById(){
        viewModelScope.launch {
            artistRepo.getBy(artistId).collect {
                artistName = it?.name?: ""
            }
        }
    }
}