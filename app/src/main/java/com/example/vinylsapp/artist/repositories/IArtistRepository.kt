package com.example.vinylsapp.artist.repositories

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.artist.models.Artist
import kotlinx.coroutines.flow.StateFlow

interface IArtistRepository {
    fun getAll(): StateFlow<List<Artist>>
    suspend fun fetchAll()
    suspend fun getBy(artistId: Int): StateFlow<Artist?>
    suspend fun addToAlbum(artist: Artist, album: Album)
    suspend fun refreshData(): List<Artist>
}
