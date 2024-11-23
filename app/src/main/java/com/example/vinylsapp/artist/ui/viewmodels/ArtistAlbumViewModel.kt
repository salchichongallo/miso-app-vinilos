package com.example.vinylsapp.artist.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.repositories.IAlbumRepository
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.IArtistRepository
import kotlinx.coroutines.launch

class ArtistAlbumViewModel(
    private val artistRepo: IArtistRepository,
    private val artistId: Int,
    albumRepo: IAlbumRepository,
) : ViewModel() {

    private val _selectedAlbum = mutableStateOf<Album?>(null)
    val selectedAlbum: State<Album?> = _selectedAlbum
    var artist by mutableStateOf<Artist?>(null)
    val albums = albumRepo.getAll()
    var isSuccessModalVisible by mutableStateOf(false)
    var isErrorModalVisible by mutableStateOf(false)

    init {
        loadArtistById()
    }

    fun addToAlbum() {
        viewModelScope.launch {
            if (selectedAlbum.value != null && artist != null) {
                try {
                    artistRepo.addToAlbum(artist!!, selectedAlbum.value!!)
                    isSuccessModalVisible = true
                    isErrorModalVisible = false
                    _selectedAlbum.value = null
                } catch (e: Exception) {
                    isErrorModalVisible = true
                    isSuccessModalVisible = false
                }

            }
        }
    }

    fun selectAlbum(album: Album) {
        _selectedAlbum.value = album
    }

    private fun loadArtistById() {
        viewModelScope.launch {
            try {
                artistRepo.getBy(artistId).collect {
                    artist = it
                }
            } catch (e: Exception) {
                artist = null
            }

        }
    }
}
