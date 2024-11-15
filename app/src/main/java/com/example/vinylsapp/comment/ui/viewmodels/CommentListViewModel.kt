package com.example.vinylsapp.comment.ui.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinylsapp.comment.models.Comment
import com.example.vinylsapp.comment.repositories.ICommentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CommentListViewModel(private val commentRepo: ICommentRepository, private val albumId: Int) : ViewModel() {
    var comments = MutableStateFlow<List<Comment>>(emptyList())
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
                commentRepo.fetchAll(albumId)
                commentRepo.getAll(albumId).collect {
                    newComments -> comments.value = newComments
                }
            } catch (e: Exception) {
                comments.value = emptyList()
            }
        }
    }
}