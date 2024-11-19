package com.example.vinylsapp.album.models

data class AlbumCreate(
    val name: String,
    val cover: String,
    val releaseDate: String,
    val genre: AlbumGenre?,
    val recordLabel: AlbumRecordLabel?,
    val description: String
)