package com.example.vinylsapp.album.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vinylsapp.album.models.AlbumCreate
import com.example.vinylsapp.album.models.AlbumGenre
import com.example.vinylsapp.album.models.AlbumRecordLabel
import com.example.vinylsapp.comment.repositories.ICommentRepository

class AlbumCreateViewModel(private val commentRepo: ICommentRepository) : ViewModel() {
    var album by mutableStateOf(
        AlbumCreate(
            name = "",
            cover = "",
            releaseDate = "",
            genre = null,
            recordLabel = null,
            description = ""
        )
    )

    fun updateAlbum(name: String? = null, cover: String? = null, genre: AlbumGenre? = null, recordLabel: AlbumRecordLabel? = null, description: String? = null) {
        album = album.copy(
            name = name ?: album.name,
            cover = cover ?: album.cover,
            genre = genre ?: album.genre,
            recordLabel = recordLabel ?: album.recordLabel,
            description = description ?: album.description
        )
    }
}