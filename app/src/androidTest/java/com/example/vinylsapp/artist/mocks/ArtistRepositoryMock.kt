package com.example.vinylsapp.artist.mocks

import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.IArtistRepository

class ArtistRepositoryMock(private val artist: List<Artist>) : IArtistRepository {
    override suspend fun getAll(): List<Artist> {
        return artist
    }
}