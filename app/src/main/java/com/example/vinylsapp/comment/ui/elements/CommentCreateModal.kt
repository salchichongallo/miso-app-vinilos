package com.example.vinylsapp.comment.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.vinylsapp.comment.ui.viewmodels.CommentCreateViewModel
import com.example.vinylsapp.comment.ui.viewmodels.CommentListViewModel

@Composable
fun CommentCreateModal(viewModel: CommentCreateViewModel, commentListViewModel: CommentListViewModel) {
    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                Text(
                    text = "Opina sobre el álbum",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    RatingBar(
                        stars = viewModel.rating,
                        size = 40.dp,
                        onPressed = {
                            viewModel.onRatingChange(it)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    modifier = Modifier.padding(bottom = 8.dp),
                    value = viewModel.description,
                    onValueChange = { viewModel.onDescriptionChange(it) },
                    label = { Text("Comentario") },
                    placeholder = { Text(text = "Ingresa un comentario") },
                    supportingText = {
                        if (viewModel.errorMessage != null) {
                            Text(
                                text = viewModel.errorMessage ?: "",
                                color = colorScheme.error,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = {
                            commentListViewModel.closeCommentCreateModal()
                            viewModel.resetForm()
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Cancelar")
                    }
                    TextButton(
                        onClick = {
                            viewModel.create()
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Comentar")
                    }
                }
            }
        }
    }
}