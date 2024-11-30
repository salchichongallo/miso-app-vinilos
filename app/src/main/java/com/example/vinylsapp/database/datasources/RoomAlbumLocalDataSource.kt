package com.example.vinylsapp.database.datasources

import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumNew
import com.example.vinylsapp.album.repositories.IAlbumRepository
import com.example.vinylsapp.database.dao.AlbumDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RoomAlbumLocalDataSource(
    private val albumDao: AlbumDao,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : IAlbumRepository {

    override fun getAll(): StateFlow<List<Album>> {
        return flow {
            val albumEntities = albumDao.getAll()
            emit(albumEntities.map { it.toAlbum() })
        }.stateIn(
            scope = CoroutineScope(dispatcherIO),
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    override suspend fun fetchAll() {
        throw UnsupportedOperationException("fetchAll no está implementado en RoomAlbumLocalDataSource")
    }

    override suspend fun getOne(albumId: Int): Album {
        throw UnsupportedOperationException("getOne no está implementado en RoomAlbumLocalDataSource")
    }

    override suspend fun add(album: AlbumNew) {
        throw UnsupportedOperationException("add no está implementado en RoomAlbumLocalDataSource")
    }
}
