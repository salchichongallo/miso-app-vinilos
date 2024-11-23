package com.example.vinylsapp.album.utils.validators

fun validateAlbumDescription(description: String): String? {
    return when {
        description.isBlank() -> "Este campo es obligatorio."
        description.length < 10 -> "Mínimo 10 caracteres."
        description.length > 100 -> "Máximo 100 caracteres."
        else -> null
    }
}