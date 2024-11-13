package com.example.vinylsapp.comment.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.comment.repositories.ICommentRepository
import kotlinx.coroutines.launch

class CommentCreateViewModel(private val commentRepo: ICommentRepository, private val albumId: Int) : ViewModel() {
    var rating by mutableStateOf<Int>(0)
    var description by mutableStateOf<String>("")

    fun create(): Unit {
        viewModelScope.launch {
            try {
                commentRepo.create(rating, description, albumId)
                // TODO: Cerrar dialog de form
                showSuccess()
            } catch (e: Exception) {
                showError()
            }
        }
    }

    private fun showSuccess() {
        // TODO: Mostrar dialog success
    }

    private fun showError() {
        // TODO: Mostrar dialog error
    }
}