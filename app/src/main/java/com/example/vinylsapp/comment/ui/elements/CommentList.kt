package com.example.vinylsapp.comment.ui.elements

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import com.example.vinylsapp.comment.models.Comment

@Composable
fun CommentList(comments: List<Comment>) {
    LazyColumn {
        items(comments) {
            CommentListItem(comment = it)
            Divider()
        }
    }
}
