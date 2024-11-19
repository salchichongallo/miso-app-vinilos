package com.example.vinylsapp.album.repositories

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumNew

interface IAlbumRepository {
    suspend fun getAll(): List<Album>
    suspend fun getOne(albumId: Int): Album
    suspend fun add(album: AlbumNew)
}
