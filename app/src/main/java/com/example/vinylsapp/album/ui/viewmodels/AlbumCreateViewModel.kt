package com.example.vinylsapp.album.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.models.AlbumErrors
import com.example.vinylsapp.album.models.AlbumNew
import com.example.vinylsapp.album.models.AlbumGenre
import com.example.vinylsapp.album.models.AlbumRecordLabel
import com.example.vinylsapp.album.repositories.IAlbumRepository
import com.example.vinylsapp.album.utils.validators.validateAlbumCover
import com.example.vinylsapp.album.utils.validators.validateAlbumDescription
import com.example.vinylsapp.album.utils.validators.validateAlbumName
import com.example.vinylsapp.album.utils.validators.validateAlbumReleaseDate
import kotlinx.coroutines.launch
import java.util.Date

class AlbumCreateViewModel(private val albumRepository: IAlbumRepository) : ViewModel() {
    var successDialogVisible by mutableStateOf(false)
    var errorDialogVisible by mutableStateOf(false)
    var isFormValid by mutableStateOf(false)

    var album by mutableStateOf(
        AlbumNew(
            name = "",
            cover = "",
            releaseDate = null,
            genre = null,
            recordLabel = null,
            description = ""
        )
    )
        private set

    var albumErrors by mutableStateOf(AlbumErrors())
        private set

    fun updateAlbumName(name: String) {
        album = album.copy(name = name)
        albumErrors = albumErrors.copy(nameError = validateAlbumName(name))
    }

    fun updateAlbumCover(cover: String) {
        album = album.copy(cover = cover)
        albumErrors = albumErrors.copy(coverError = validateAlbumCover(cover))
    }

    fun updateAlbumReleaseDate(releaseDate: Date) {
        album = album.copy(releaseDate = releaseDate)
        albumErrors = albumErrors.copy(releaseDateError = validateAlbumReleaseDate(releaseDate))
    }

    fun updateAlbumGenre(genre: AlbumGenre) {
        album = album.copy(genre = genre)
    }

    fun updateAlbumRecordLabel(recordLabel: AlbumRecordLabel) {
        album = album.copy(recordLabel = recordLabel)
    }

    fun updateAlbumDescription(description: String) {
        album = album.copy(description = description)
        albumErrors = albumErrors.copy(descriptionError = validateAlbumDescription(description))
    }

    fun create() {
        if (!verifiyIfFormIsValid()) return
        viewModelScope.launch {
            try {
                albumRepository.add(album)
                showSuccessDialog()
                resetForm()
            } catch (e: Exception) {
                showErrorDialog()
            }
        }

    }

    private fun verifiyIfFormIsValid(): Boolean {
        isFormValid = albumErrors.nameError == null &&
                albumErrors.coverError == null &&
                albumErrors.releaseDateError == null &&
                albumErrors.descriptionError == null &&
                album.genre != null &&
                album.recordLabel != null
        return isFormValid
    }

    private fun showSuccessDialog() {
        successDialogVisible = true
        errorDialogVisible = false
    }

    private fun showErrorDialog() {
        successDialogVisible = false
        errorDialogVisible = true
    }

    private fun resetForm() {
        album = AlbumNew(
            name = "",
            cover = "",
            releaseDate = null,
            genre = null,
            recordLabel = null,
            description = ""
        )
        albumErrors = AlbumErrors()
    }
}