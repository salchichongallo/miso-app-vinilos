package com.example.vinylsapp.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.vinylsapp.artist.models.Artist

@Dao
interface ArtistsDao {
    @Query("SELECT * FROM artists_table")
    fun getAll(): List<Artist>
}