package com.example.vinylsapp.album.mocks

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumNew
import com.example.vinylsapp.album.repositories.IAlbumRepository

class AlbumRepositoryMock(private val albums: List<Album>) : IAlbumRepository {
    override suspend fun getAll(): List<Album> {
        return albums
    }

    override suspend fun getOne(albumId: Int): Album {
        val album = albums.find { it.id == albumId }
        if (album == null) {
            throw Exception("The given album id '$albumId' was not found")
        }
        return album
    }

    override suspend fun add(album: AlbumNew) {
        TODO("Not yet implemented")
    }
}
