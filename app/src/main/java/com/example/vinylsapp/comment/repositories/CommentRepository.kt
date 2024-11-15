package com.example.vinylsapp.comment.repositories

import com.example.vinylsapp.comment.models.Comment
import com.example.vinylsapp.comment.repositories.models.NewComment
import com.example.vinylsapp.comment.repositories.services.NetworkCommentServiceAdapter

class CommentRepository(private val serviceAdapter: NetworkCommentServiceAdapter) : ICommentRepository {
    override suspend fun getAll(albumId: Int): List<Comment> {
        return serviceAdapter.fetchComments(albumId)
    }

    override suspend fun create(rating: Int, description: String, albumId: Int): Comment {
        val collector = 1
        val newComment = NewComment(rating, description, collector)
        return serviceAdapter.createComment(newComment, albumId)
    }
}