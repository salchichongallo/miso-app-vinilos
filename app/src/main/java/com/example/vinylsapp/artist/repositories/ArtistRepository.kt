package com.example.vinylsapp.artist.repositories

import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.services.NetworkArtistServiceAdapter

class ArtistRepository(private val serviceAdapter: NetworkArtistServiceAdapter) :
    IArtistRepository {
    override suspend fun getAll(): List<Artist> {
        return serviceAdapter.fetchArtists()
    }
}
