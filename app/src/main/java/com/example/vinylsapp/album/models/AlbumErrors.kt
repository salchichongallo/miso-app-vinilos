package com.example.vinylsapp.album.models

data class AlbumErrors(
    val nameError: String? = null,
    val coverError: String? = null,
    val releaseDateError: String? = null,
    val genreError: String? = null,
    val recordLabelError: String? = null,
    val descriptionError: String? = null
)
