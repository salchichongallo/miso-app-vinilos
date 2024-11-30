package com.example.vinylsapp.artist.models

import com.example.vinylsapp.album.models.Album

data class Artist(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<Album> = emptyList(),
)
