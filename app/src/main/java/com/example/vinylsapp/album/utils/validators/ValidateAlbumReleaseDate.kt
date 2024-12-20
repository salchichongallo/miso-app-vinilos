package com.example.vinylsapp.album.utils.validators

import java.util.Date

fun validateAlbumReleaseDate(releaseDate: Date): String? {
    val today = Date()
    return if (releaseDate.before(today)) {
        "La fecha de lanzamiento no puede ser anterior a la de hoy."
    } else {
        null
    }
}
