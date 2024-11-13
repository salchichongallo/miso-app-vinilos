package com.example.vinylsapp.comment.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.comment.models.Comment
import com.example.vinylsapp.comment.repositories.ICommentRepository
import kotlinx.coroutines.launch

class CommentListViewModel(private val commentRepo: ICommentRepository, private val albumId: Int) : ViewModel() {
    var comments by mutableStateOf(listOf<Comment>())

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                comments = commentRepo.getAll(albumId)
            } catch (e: Exception) {
                comments = listOf()
            }
        }
    }
}