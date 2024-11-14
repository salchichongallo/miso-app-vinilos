package com.example.vinylsapp.comment.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vinylsapp.comment.ui.viewmodels.CommentCreateViewModel
import com.example.vinylsapp.comment.ui.viewmodels.CommentListViewModel
import com.example.vinylsapp.ui.elements.VinylsBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentLitScreen(viewModel: CommentListViewModel, commentCreateViewModel: CommentCreateViewModel, navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
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
            FloatingActionButton(
                onClick = {
                    viewModel.openCommentCreateModal()
                },
                modifier = Modifier.padding(16.dp),
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Comentar",
                    )
                    Text("Comentar")
                }
            }
        }
    ) {
        innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                CommentList(viewModel.comments)

                if (viewModel.isCommentCreateModalVisible) {
                    CommentCreateModal(commentCreateViewModel, viewModel)
                }
            }
    }
}