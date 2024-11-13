package com.example.vinylsapp.comment.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.comment.models.Comment

@Composable
fun CommentListItem(comment: Comment) {
    ListItem(
        headlineContent = {
            RatingBar(stars = comment.rating)
            Box(modifier = Modifier.height(8.dp))
        },
        supportingContent = {
            Text(comment.description)
            Box(modifier = Modifier.height(8.dp))
        },
    )
}
