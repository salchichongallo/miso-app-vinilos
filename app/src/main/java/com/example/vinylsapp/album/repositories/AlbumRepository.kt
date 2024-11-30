package com.example.vinylsapp.album.repositories

import android.util.Log
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumNew
import com.example.vinylsapp.album.repositories.services.NetworkAlbumServiceAdapter
import com.example.vinylsapp.database.dao.AlbumDao
import com.example.vinylsapp.database.entities.AlbumEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumRepository(
    private val serviceAdapter: NetworkAlbumServiceAdapter,
    private val albumDao: AlbumDao
) : IAlbumRepository {
    private val albums = MutableStateFlow(value = emptyList<Album>())

    override suspend fun fetchAll() {
        try {
            // Ejecutar acceso a la base de datos en un contexto IO
            val cachedAlbums = withContext(Dispatchers.IO) {
                albumDao.getAll().map { it.toAlbum() }
            }
            if (cachedAlbums.isNotEmpty()) {
                albums.emit(cachedAlbums) // Emitir los datos de la caché si existen
            } else {
                // Si no hay datos en la caché, obtenerlos de la API
                val apiAlbums = serviceAdapter.fetchAlbums()
                withContext(Dispatchers.IO) {
                    albumDao.insertAll(apiAlbums.map { AlbumEntity.fromAlbum(it) }) // Guardar en la caché
                }
                albums.emit(apiAlbums) // Emitir los datos obtenidos de la API
            }
        } catch (error: Exception) {
            Log.e("AlbumRepository", "Error fetching albums", error)
            throw error
        }
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
