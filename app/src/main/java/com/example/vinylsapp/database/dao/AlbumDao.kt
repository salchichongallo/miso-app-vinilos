package com.example.vinylsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.database.entities.AlbumEntity

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    fun getAll(): List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<AlbumEntity>)
}