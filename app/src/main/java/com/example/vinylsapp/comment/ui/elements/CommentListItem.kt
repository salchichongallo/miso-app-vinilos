package com.example.vinylsapp.comment.ui.elements

import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.vinylsapp.comment.models.Comment

@Composable
fun CommentListItem(comment: Comment) {
    ListItem(
        headlineContent = {
            Text("Headline goes here")
        },
        supportingContent = {
            Text(comment.description)
        },
    )
}
