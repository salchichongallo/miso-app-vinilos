package com.example.vinylsapp.artist.repositories

import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.artist.repositories.services.NetworkArtistServiceAdapter
import com.example.vinylsapp.database.dao.ArtistsDao

class ArtistRepository(private val serviceAdapter: NetworkArtistServiceAdapter, private val artistsDao: ArtistsDao) :
    IArtistRepository {
    override suspend fun getAll(): List<Artist> {
        return serviceAdapter.fetchArtists()
    }

    override suspend fun refreshData(): List<Artist> {
        try {
            val cached = artistsDao.getAll()
            return cached.ifEmpty {
                getAll()
            }
        } catch (e: Exception) {
            return getAll()
        }
    }


}
