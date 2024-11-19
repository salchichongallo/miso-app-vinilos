package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.album.ui.viewmodels.AlbumCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCreateForm(viewModel: AlbumCreateViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = viewModel.album.name,
            onValueChange = {
                viewModel.updateAlbumName(name = it)
            },
            label = { Text("Nombre") },
            placeholder = { Text("Ingrese el nombre del álbum") },
            isError = viewModel.albumErrors.nameError != null,
            supportingText = {
                if (viewModel.albumErrors.nameError != null) {
                    Text(
                        text = viewModel.albumErrors.nameError!!,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = viewModel.album.cover,
            onValueChange = {
                viewModel.updateAlbumCover(cover = it)
            },
            label = { Text("Cover") },
            placeholder = { Text("Ingrese el cover del álbum") },
            isError = viewModel.albumErrors.coverError != null,
            supportingText = {
                if (viewModel.albumErrors.coverError != null) {
                    Text(
                        text = viewModel.albumErrors.coverError!!,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )

        AlbumReleaseDateField(
            albumReleaseDateError = viewModel.albumErrors.releaseDateError,
            onSelectedDate = {
                viewModel.updateAlbumReleaseDate(releaseDate = it)
            }
        )

        AlbumGenreDropdown(
            selectedGenre = viewModel.album.genre,
            onGenreSelected = { genre -> viewModel.updateAlbumGenre(genre = genre) }
        )

        AlbumRecordLabelDropdown(
            selectedLabel = viewModel.album.recordLabel,
            onLabelSelected = { label -> viewModel.updateAlbumRecordLabel(recordLabel = label) }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = viewModel.album.description,
            onValueChange = {
                viewModel.updateAlbumDescription(description = it)
            },
            placeholder = { Text("Ingrese la descripción del álbum") },
            label = { Text("Descripción") },
            maxLines = 5,
            minLines = 5,
            isError = viewModel.albumErrors.descriptionError != null,
            supportingText = {
                if (viewModel.albumErrors.descriptionError != null) {
                    Text(
                        text = viewModel.albumErrors.descriptionError!!,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )

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
            ) {
                Text("Cancelar y volver")
            }

            Button(
                enabled = viewModel.isFormValid(),
                onClick = {
                    viewModel.create()
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Crear")
            }
        }
    }
}