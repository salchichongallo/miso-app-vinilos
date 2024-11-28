package com.example.vinylsapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.models.AlbumGenre

@Entity(tableName = "albums")
class AlbumEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val cover: String,
    val genre: AlbumGenre,
    val releaseDate: String,
) {
    fun toAlbum(): Album {
        return Album(
            id = id,
            name = name,
            cover = cover,
            genre = genre,
            releaseDate = releaseDate,
        )
    }

    companion object {
        fun fromAlbum(album: Album): AlbumEntity {
            return AlbumEntity(
                id = album.id,
                name = album.name,
                cover = album.cover,
                genre = album.genre,
                releaseDate = album.releaseDate,
            )
        }
    }

}