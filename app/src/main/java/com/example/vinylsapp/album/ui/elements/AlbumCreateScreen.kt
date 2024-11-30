package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.vinylsapp.album.ui.viewmodels.AlbumCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCreateScreen(
    viewModel: AlbumCreateViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear nuevo álbum") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
            )
        },
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            AlbumCreateForm(viewModel, navController)

            if (viewModel.successDialogVisible) {
                AlbumCreateSuccessAlert(
                    navController,
                    onDismiss = {
                        viewModel.successDialogVisible = false
                    }
                )
            }

            if (viewModel.errorDialogVisible) {
                AlbumCreateErrorAlert(
                    onDismiss = {
                        viewModel.errorDialogVisible = false
                    }
                )
            }
        }
    }
}