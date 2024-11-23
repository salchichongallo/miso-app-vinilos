package com.example.vinylsapp.album.repositories

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumNew
import com.example.vinylsapp.album.repositories.services.NetworkAlbumServiceAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AlbumRepository(private val serviceAdapter: NetworkAlbumServiceAdapter) : IAlbumRepository {
    private val albums = MutableStateFlow(value = emptyList<Album>())

    override suspend fun fetchAll() {
        albums.emit(value = serviceAdapter.fetchAlbums())
    }

    override fun getAll(): StateFlow<List<Album>> {
        return albums
    }

    override suspend fun getOne(albumId: Int): Album {
        return serviceAdapter.fetchAlbum(albumId)
    }

    override suspend fun add(album: AlbumNew) {
        val newAlbum = serviceAdapter.addAlbum(album)
        albums.update { it + newAlbum }
    }
}
