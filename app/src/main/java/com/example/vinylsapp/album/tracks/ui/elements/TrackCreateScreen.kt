package com.example.vinylsapp.album.tracks.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.R
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackCreateScreen(viewModel: TrackCreateViewModel, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar track") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        },
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.music_placeholder),
                    contentDescription = "Ilustración de canción",
                    modifier = Modifier.size(250.dp)
                )
                Text("Agregar el track al álbum ${viewModel.album.name}")

                TrackNewForm(viewModel, navController)

                if (viewModel.isSuccessModalVisible) {
                    AlertDialog(
                        onDismissRequest = { viewModel.isSuccessModalVisible = false },
                        title = { Text("Track asociado") },
                        text = { Text("El track ha sido asociado al álbum ${viewModel.album.name}") },
                        confirmButton = {
                            Button(onClick = { viewModel.isSuccessModalVisible = false }) {
                                Text("Aceptar")
                            }
                        }
                    )
                }

                if (viewModel.isErrorModalVisible) {
                    AlertDialog(
                        onDismissRequest = { viewModel.isErrorModalVisible = false },
                        title = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Ha ocurrido un error")
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Rounded.Error,
                                    contentDescription = "Error Icon",
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        text = { Text("Ocurrió un error al agregar el track, intenta nuevamente por favor.") },
                        confirmButton = {
                            Button(onClick = { viewModel.isErrorModalVisible = false }) {
                                Text("Listo")
                            }
                        }
                    )
                }
            }
        }
    }
}
