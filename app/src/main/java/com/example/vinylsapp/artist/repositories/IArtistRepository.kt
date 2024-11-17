package com.example.vinylsapp.artist.repositories

import com.example.vinylsapp.artist.models.Artist

interface IArtistRepository {
    suspend fun getAll(): List<Artist>
    suspend fun refreshData(): List<Artist>
}
