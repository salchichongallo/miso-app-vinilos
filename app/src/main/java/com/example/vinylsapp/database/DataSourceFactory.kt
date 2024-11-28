package com.example.vinylsapp.database

import android.content.Context
import com.example.vinylsapp.database.datasources.RoomAlbumLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher

object DataSourceFactory {
    fun createDataSource(
        context: Context,
        dispatcherIO: CoroutineDispatcher
    ): RoomAlbumLocalDataSource {
        val database = VinylsRoomDatabase.getDatabase(context)
        return RoomAlbumLocalDataSource(database.albumDao(), dispatcherIO)
    }
}