package com.example.vinylsapp.album.models
import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val genre: AlbumGenre,
    val releaseDate: String,
)
