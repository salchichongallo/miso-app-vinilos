package com.example.vinylsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vinylsapp.database.dao.AlbumDao
import com.example.vinylsapp.database.entities.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1)
abstract class VinylsRoomDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: VinylsRoomDatabase? = null

        fun getDatabase(context: Context): VinylsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VinylsRoomDatabase::class.java,
                    "vinyls_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}