package com.example.vinylsapp.artist.ui.elements

import android.webkit.WebSettings.TextSize
import androidx.compose.animation.core.copy
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.size.Size
import com.example.vinylsapp.R
import com.example.vinylsapp.artist.ui.viewmodels.ArtistAlbumViewModel
import com.example.vinylsapp.ui.elements.VinylsBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistAlbumScreen(navController: NavController, viewModel: ArtistAlbumViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar artista a álbum") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
            )
        },
        bottomBar = { VinylsBottomAppBar(navController) },
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.music_placeholder),
                    contentDescription = "Ilustración de canción",
                    modifier = Modifier.size(250.dp)
                    .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Agregar ${viewModel.artist?.name ?: "Seleccione el artista"} a álbum ${viewModel.selectedAlbum.value?.name ?: "'seleccione el álbum'"}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)
                )

                Text("Seleccionar álbum", textAlign = TextAlign.Start, style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp) )
                LazyColumn(){
                    items(viewModel.albums.size){ index ->
                        AlbumPreviewItem(album = viewModel.albums[index], onSelect = {
                            viewModel.selectAlbum(album = viewModel.albums[index])
                        }, isSelect = viewModel.albums[index] == viewModel.selectedAlbum.value)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("CancelArtistAlbumButton")
                    ) {
                        Text("Cancelar y volver")
                    }

                    Button(
                        enabled = viewModel.selectedAlbum.value != null,
                        onClick = { viewModel.addToAlbum() },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("ArtistAlbumButton")
                    ) {
                        Text("Agregar")
                    }
                }
            }

            if (viewModel.isSuccessModalVisible) {
                ArtistAlbumSuccessAlert(onDismiss = { viewModel.isSuccessModalVisible = false })
            }

            if (viewModel.isErrorModalVisible) {
                ArtistAlbumErrorAlert(onDismiss = { viewModel.isErrorModalVisible = false })
            }
        }
    }
}


