package com.example.vinylsapp.album.repositories

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumNew
import com.example.vinylsapp.album.repositories.services.NetworkAlbumServiceAdapter

class AlbumRepository(private val serviceAdapter: NetworkAlbumServiceAdapter) : IAlbumRepository {
    override suspend fun getAll(): List<Album> {
        return serviceAdapter.fetchAlbums()
    }

    override suspend fun getOne(albumId: Int): Album {
        return serviceAdapter.fetchAlbum(albumId)
    }

    override suspend fun add(album: AlbumNew) {
        serviceAdapter.addAlbum(album)
    }
}
