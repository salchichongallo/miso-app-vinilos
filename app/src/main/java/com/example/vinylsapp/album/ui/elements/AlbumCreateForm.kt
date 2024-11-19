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
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = viewModel.album.name,
            onValueChange = {
                viewModel.updateAlbum(name = it)
            },
            label = { Text("Nombre") },
            placeholder = { Text("Ingrese el nombre del álbum") },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = viewModel.album.cover,
            onValueChange = {
                viewModel.updateAlbum(cover = it)
            },
            label = { Text("cover") },
            placeholder = { Text("Ingrese el cover del álbum") },
        )

        AlbumReleaseDateField()

        AlbumGenreDropdown(
            selectedGenre = viewModel.album.genre,
            onGenreSelected = { genre -> viewModel.updateAlbum(genre = genre) }
        )

        AlbumRecordLabelDropdown(
            selectedLabel = viewModel.album.recordLabel,
            onLabelSelected = { label -> viewModel.updateAlbum(recordLabel = label) }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = viewModel.album.description,
            onValueChange = {
                viewModel.updateAlbum(description = it)
            },
            placeholder = { Text("Ingrese la descripción del álbum") },
            label = { Text("Descripción") },
            maxLines = 5,
            minLines = 5,
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
                onClick = { },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Crear")
            }
        }
    }
}