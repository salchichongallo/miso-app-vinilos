package com.example.vinylsapp.album.mocks

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumNew
import com.example.vinylsapp.album.repositories.IAlbumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AlbumRepositoryMock(private val albums: List<Album>) : IAlbumRepository {
    private val _albums = MutableStateFlow(albums)

    override suspend fun fetchAll() {}

    override fun getAll(): StateFlow<List<Album>> {
        return _albums
    }

    override suspend fun getOne(albumId: Int): Album {
        val album = albums.find { it.id == albumId }
        if (album == null) {
            throw Exception("The given album id '$albumId' was not found")
        }
        return album
    }

    override suspend fun add(album: AlbumNew) {
        val newAlbum = Album(
            id = 100,
            name = album.name,
            cover = album.cover,
            releaseDate = "2024-01-1", // TODO: update with given `album`
            genre = album.genre!!,
        )
        _albums.update { it + newAlbum }
    }
}
