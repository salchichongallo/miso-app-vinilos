package com.example.vinylsapp.comment.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.comment.models.Comment
import com.example.vinylsapp.comment.repositories.ICommentRepository
import kotlinx.coroutines.launch

class CommentListViewModel(private val commentRepo: ICommentRepository, private val albumId: Int) : ViewModel() {
    var comments = mutableStateListOf<Comment>()
    var isCommentCreateModalVisible by mutableStateOf(false)

    init {
        load()
    }

    fun openCommentCreateModal() {
        isCommentCreateModalVisible = true
    }

    fun closeCommentCreateModal() {
        isCommentCreateModalVisible = false
    }

    private fun load() {
        viewModelScope.launch {
            try {
                val loadedComments = commentRepo.getAll(albumId)
                comments.clear()
                comments.addAll(loadedComments)
            } catch (e: Exception) {
                comments.clear()
            }
        }
    }
}