package com.example.vinylsapp.comment.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vinylsapp.comment.ui.viewmodels.CommentCreateViewModel
import com.example.vinylsapp.comment.ui.viewmodels.CommentListViewModel
import com.example.vinylsapp.ui.elements.VinylsBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentLitScreen(viewModel: CommentListViewModel, commentCreateViewModel: CommentCreateViewModel, navController: NavController) {
    val comments by viewModel.comments.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.testTag("BackButton")) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Comentarios del álbum",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 12.dp)
                )
            }
        },
        bottomBar = { VinylsBottomAppBar(navController) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.testTag("CreateCommentButton"),
                onClick = { viewModel.openCommentCreateModal() },
                icon = {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Comentar"
                    )
                },
                text = { Text(text = "Comentar") },
            )
        }
    ) {
        innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                CommentList(comments)

                if (viewModel.isCommentCreateModalVisible) {
                    CommentCreateModal(commentCreateViewModel, viewModel)
                }
            }
    }
}