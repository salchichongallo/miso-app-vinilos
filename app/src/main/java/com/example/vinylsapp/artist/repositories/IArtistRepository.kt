package com.example.vinylsapp.artist.repositories

import com.example.vinylsapp.artist.models.Artist
import kotlinx.coroutines.flow.StateFlow

interface IArtistRepository {
    fun getAll(): StateFlow<List<Artist>>
    suspend fun fetchAll()
}
