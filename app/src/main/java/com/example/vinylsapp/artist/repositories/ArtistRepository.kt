package com.example.vinylsapp.artist.repositories

import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.services.NetworkArtistServiceAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArtistRepository(private val serviceAdapter: NetworkArtistServiceAdapter) :
    IArtistRepository {

    private val artists = MutableStateFlow(value = listOf<Artist>())

    override fun getAll(): StateFlow<List<Artist>> {
        return artists
    }

    override suspend fun fetchAll() {
        artists.emit(value = serviceAdapter.fetchArtists())
    }
}
