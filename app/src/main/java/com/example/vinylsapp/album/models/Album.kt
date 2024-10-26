package com.example.vinylsapp.album.models

data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val genre: AlbumGenre,
)
