package com.example.vinylsapp

import android.app.Application
import com.example.vinylsapp.database.DataSourceFactory
import com.example.vinylsapp.database.datasources.RoomAlbumLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class VynilsApplication : Application() {
    val dispatcherIO: CoroutineDispatcher
        get() = Dispatchers.IO

    val dataSource: RoomAlbumLocalDataSource
        get() = DataSourceFactory.createDataSource(this, dispatcherIO)

}