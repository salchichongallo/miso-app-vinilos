package com.example.vinylsapp.album.models

import com.google.gson.annotations.SerializedName

enum class AlbumGenre(val value: String) {
    @SerializedName("Classical")
    CLASSICAL("Classical"),

    @SerializedName("Salsa")
    SALSA("Salsa"),

    @SerializedName("Rock")
    ROCK("Rock"),

    @SerializedName("Folk")
    FOLk("Folk"),
}
