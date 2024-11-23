package com.example.vinylsapp.album.models

import com.google.gson.annotations.SerializedName

enum class AlbumRecordLabel(val value: String) {
    @SerializedName("Sony Music")
    SONY("Sony Music"),

    @SerializedName("EMI")
    EMI("EMI"),

    @SerializedName("Discos Fuentes")
    FUENTES("Discos Fuentes"),

    @SerializedName("Elektra")
    ELEKTRA("Elektra"),

    @SerializedName("Fania Records")
    FANIA("Fania Records"),
}
