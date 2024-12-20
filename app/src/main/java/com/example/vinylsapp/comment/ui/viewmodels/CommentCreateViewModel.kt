package com.example.vinylsapp.comment.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.comment.repositories.ICommentRepository
import kotlinx.coroutines.launch

class CommentCreateViewModel(
    private val commentRepo: ICommentRepository,
    private val albumId: Int
) : ViewModel() {
    var rating by mutableIntStateOf(0)
    var description by mutableStateOf("")

    private val maxCommentLength = 500
    var errorMessage by mutableStateOf<String?>(null)

    var isCreatingComment by mutableStateOf(true)
    var isSuccessAlert by mutableStateOf(false)
    var isErrorAlert by mutableStateOf(false)

    fun create() {
        if (!isValidForm()) return

        viewModelScope.launch {
            try {
                commentRepo.create(rating, description, albumId)
                showSuccess()
                resetForm()
            } catch (e: Exception) {
                showError()
            }
        }
    }

    fun resetForm() {
        rating = 0
        description = ""
        errorMessage = null
    }

    fun onDescriptionChange(newDescription: String) {
        if (newDescription.length <= maxCommentLength) {
            description = newDescription
            errorMessage = null
        } else {
            errorMessage = "Supera los $maxCommentLength caracteres"
        }
    }

    fun onRatingChange(newRating: Int) {
        rating = newRating
        errorMessage = null
    }

    fun acceptError() {
        isCreatingComment = true
        isErrorAlert = false
    }

    fun resetAll() {
        resetForm()
        isCreatingComment = true
        isSuccessAlert = false
        isErrorAlert = false
    }

    private fun isValidForm(): Boolean {
        if (rating == 0) {
            errorMessage = "El puntaje es obligatorio"
            return false
        }

        if (description.length > maxCommentLength) {
            errorMessage = "Supera los $maxCommentLength caracteres"
            return false
        }

        errorMessage = null
        return true
    }

    private fun showSuccess() {
        isSuccessAlert = true
        isCreatingComment = false
        isErrorAlert = false
    }

    private fun showError() {
        isErrorAlert = true
        isSuccessAlert = false
        isCreatingComment = false
    }
}