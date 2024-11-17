package com.example.vinylsapp.comment.mocks

import com.example.vinylsapp.comment.models.Comment
import com.example.vinylsapp.comment.repositories.ICommentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CommentRepositoryMock : ICommentRepository {
    private val comments = mutableListOf<Comment>()
    private val commentsFlow = MutableStateFlow<List<Comment>>(emptyList())
    private var commentIdCounter = 1

    override suspend fun getAll(albumId: Int): StateFlow<List<Comment>> {
        return commentsFlow
    }

    override suspend fun create(rating: Int, description: String, albumId: Int) {
        val newComment = Comment(
            id = commentIdCounter++,
            rating = rating,
            description = description
        )
        comments.add(newComment)
        commentsFlow.value = comments
    }

    override suspend fun fetchAll(albumId: Int) {
        val commentMock = Comment(
            id = 1,
            rating = 5,
            description = "Excelente álbum",
        )
        comments.add(commentMock)
        commentsFlow.value = comments
    }
}