package com.example.vinylsapp.album.utils.validators

fun validateAlbumCover(cover: String): String? {
    return when {
        cover.isBlank() -> "Este campo es obligatorio."
        !cover.startsWith("https://") -> "Debe ser una URL válida."
        cover.length < 10 -> "Mínimo 10 caracteres."
        cover.length > 100 -> "Máximo 100 caracteres."
        else -> null
    }
}