package com.example.vinylsapp.album.repositories

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumNew
import kotlinx.coroutines.flow.StateFlow

interface IAlbumRepository {
    fun getAll(): StateFlow<List<Album>>
    suspend fun fetchAll()
    suspend fun getOne(albumId: Int): Album
    suspend fun add(album: AlbumNew)
}
