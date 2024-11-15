package com.example.vinylsapp.comment.ui.elements

import androidx.compose.runtime.Composable
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
            viewModel.resetAll()
        }
    } else if (viewModel.isErrorAlert) {
        CommentNewErrorAlert {
            viewModel.acceptError()
        }
    }
}