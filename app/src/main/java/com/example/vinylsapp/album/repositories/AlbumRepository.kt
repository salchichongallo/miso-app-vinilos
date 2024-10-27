package com.example.vinylsapp.album.repositories

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.repositories.services.NetworkAlbumServiceAdapter

class AlbumRepository(private val serviceAdapter: NetworkAlbumServiceAdapter) {
    suspend fun getAll(): List<Album> {
        return serviceAdapter.fetchAlbums()
    }

    suspend fun getOne(albumId: Int): Album {
        return serviceAdapter.fetchAlbum(albumId)
    }
}
