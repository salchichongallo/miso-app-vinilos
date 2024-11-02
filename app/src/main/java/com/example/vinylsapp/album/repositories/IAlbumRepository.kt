package com.example.vinylsapp.album.repositories

import com.example.vinylsapp.album.models.Album

interface IAlbumRepository {
    suspend fun getAll(): List<Album>

    suspend fun getOne(albumId: Int): Album
}
