package com.example.vinylsapp.album.tracks.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.tracks.models.TrackNew
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository
import kotlinx.coroutines.launch

class TrackCreateViewModel(
    val album: Album,
    private val trackRepo: ITrackRepository
) : ViewModel() {
    var trackName by mutableStateOf("")
    var trackDuration by mutableStateOf(TextFieldValue(""))

    var isSuccessModalVisible by mutableStateOf(false)
    var isErrorModalVisible by mutableStateOf(false)

    var isTrackNameTouched by mutableStateOf(false)
    var isTrackDurationTouched by mutableStateOf(false)

    var trackNameErrorMessage by mutableStateOf("")
    var trackDurationErrorMessage by mutableStateOf("")
    var isTrackNameValid by mutableStateOf(false)
    var isTrackDurationValid by mutableStateOf(false)

    fun onTrackNameChange(newName: String) {
        trackName = newName
        isTrackNameTouched = true
        trackNameErrorMessage = if (trackName.isEmpty()) {
            "El nombre es requerido"
        } else if (trackName.length !in 10..30) {
            "El nombre debe tener entre 10 y 30 caracteres"
        } else {
            ""
        }
        isTrackNameValid = trackName.isNotEmpty() && trackName.length in 10..30
    }

    fun onTrackDurationChange(newDuration: String) {
        isTrackDurationTouched = true

        val regex = "^([0-5][0-9]):([0-5][0-9])$".toRegex()
        trackDurationErrorMessage = if (newDuration.isEmpty()) {
            "La duración es requerida"
        } else if (!newDuration.matches(regex)) {
            "Duración inválida. El formato debe ser MM:SS (00:30 - 59:59)"
        } else {
            ""
        }
        isTrackDurationValid = newDuration.matches(regex)
    }

    fun createTrack() {
        val track = TrackNew(trackName, trackDuration.text)
        viewModelScope.launch {
            try {
                trackRepo.create(track, album.id)
                showSuccess()
                resetForm()
            } catch (e: Exception) {
                showError()
            }
        }
    }

    private fun showSuccess() {
        isSuccessModalVisible = true
    }

    private fun showError() {
        isErrorModalVisible = true
    }

    private fun resetForm() {
        trackName = ""
        trackDuration = TextFieldValue("")
        isTrackNameTouched = false
        isTrackDurationTouched = false
        trackNameErrorMessage = ""
        trackDurationErrorMessage = ""
        isTrackNameValid = false
        isTrackDurationValid = false
    }
}
