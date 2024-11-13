package com.example.vinylsapp.comment.repositories

import com.example.vinylsapp.comment.models.Comment
import com.example.vinylsapp.comment.repositories.services.NetworkCommentServiceAdapter

class CommentRepository(private val serviceAdapter: NetworkCommentServiceAdapter) : ICommentRepository {
    private var comments = mutableListOf<Comment>()

    override suspend fun getAll(albumId: Int): List<Comment> {
        return serviceAdapter.fetchComments(albumId)
    }

    override suspend fun create(rating: Int, description: String, albumId: Int) {
        val createdComment = serviceAdapter.createComment(rating, description,albumId)
        addToList(createdComment)
    }

    private fun addToList(comment: Comment): Unit {
        comments.add(0, comment)
    }
}