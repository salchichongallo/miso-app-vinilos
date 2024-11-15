package com.example.vinylsapp.comment.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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
    if (viewModel.isCreatingComment) {
        CommentCreateForm(viewModel, {
            commentListViewModel.closeCommentCreateModal()
            viewModel.resetForm()
        })
    } else if (viewModel.isSuccessAlert) {
        CommentNewSuccessAlert {
            commentListViewModel.closeCommentCreateModal()
        }
    } else if (viewModel.isErrorAlert) {
        CommentNewErrorAlert {
            viewModel.acceptError()
        }
    }
}