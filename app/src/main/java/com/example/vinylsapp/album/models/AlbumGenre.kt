package com.example.vinylsapp.album.models

import com.google.gson.annotations.SerializedName

enum class AlbumGenre(val value: String) {
    @SerializedName("Salsa")
    SALSA("Salsa"),
}
