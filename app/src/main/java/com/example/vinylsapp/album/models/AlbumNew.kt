package com.example.vinylsapp.album.models

import java.util.Date

data class AlbumNew(
    val name: String,
    val cover: String,
    val releaseDate: Date?,
    val genre: AlbumGenre?,
    val recordLabel: AlbumRecordLabel?,
    val description: String
)