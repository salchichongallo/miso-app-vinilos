package com.example.vinylsapp.album.utils.validators

fun validateAlbumName(name: String): String? {
    return when {
        name.isBlank() -> "Este campo es requerido."
        name.length < 5 -> "Mínimo 5 caracteres."
        name.length > 30 -> "Máximo 30 caracteres."
        else -> null
    }
}
