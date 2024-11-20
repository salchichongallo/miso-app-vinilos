package com.example.vinylsapp.artist.mocks

import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.IArtistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArtistRepositoryMock(private val artist: List<Artist>) : IArtistRepository {
    override fun getAll(): StateFlow<List<Artist>> {
        return MutableStateFlow(value = artist)
    }

    override suspend fun fetchAll() {}

    override suspend fun getBy(artistId: Int): StateFlow<Artist?> {
        val artist = artist.first { it.id == artistId }
        return MutableStateFlow(value = artist)
    }
}
