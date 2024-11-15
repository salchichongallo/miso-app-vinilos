package com.example.vinylsapp.comment.repositories

import com.example.vinylsapp.comment.models.Comment
import com.example.vinylsapp.comment.repositories.models.NewComment
import com.example.vinylsapp.comment.repositories.services.NetworkCommentServiceAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CommentRepository(private val serviceAdapter: NetworkCommentServiceAdapter) :
    ICommentRepository {
    private var comments = MutableStateFlow<Map<Int, List<Comment>>>(emptyMap())

    override suspend fun getAll(albumId: Int): StateFlow<List<Comment>> {
        return comments.map {
            it[albumId].orEmpty()
        }.stateIn(
            scope = CoroutineScope(Dispatchers.IO),
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
    }

    override suspend fun create(rating: Int, description: String, albumId: Int) {
        val collector = 1
        val newCommentBody = NewComment(rating, description, collector)
        val newComment = serviceAdapter.createComment(newCommentBody, albumId)
        comments.update {
            val currentAlbumComments = it[albumId].orEmpty()
            it + (albumId to listOf(newComment) + currentAlbumComments)
        }
    }

    override suspend fun fetchAll(albumId: Int) {
        if (!comments.value.containsKey(albumId)) {
            val newComments = serviceAdapter.fetchComments(albumId)
            comments.update { it + (albumId to newComments.reversed())  }
        }
    }
}