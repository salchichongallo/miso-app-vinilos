package com.example.vinylsapp.artist.repositories

import com.example.vinylsapp.artist.models.Artist

class ArtistRepository : IArtistRepository {
    override suspend fun getAll(): List<Artist> {
        return listOf()
    }
}
