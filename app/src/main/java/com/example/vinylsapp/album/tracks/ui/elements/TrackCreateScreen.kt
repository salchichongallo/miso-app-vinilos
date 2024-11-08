package com.example.vinylsapp.album.tracks.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackCreateViewModel

@Composable
fun TrackCreateScreen(viewModel: TrackCreateViewModel) {
    val trackName = viewModel.track.name
    val trackDuration = viewModel.track.duration

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = trackName,
            onValueChange = { viewModel.onTrackNameChange(it) },
            label = { Text("Nombre") },
            placeholder = { Text("Ingrese el nombre del track") },
        )

        TextField(
            value = trackDuration,
            onValueChange = { viewModel.onTrackDurationChange(it) },
            label = { Text("Duración") },
            placeholder = { Text("MM:SS") }
        )

        Row {
            Button(
                onClick = { viewModel.createTrack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar y volver")
            }

            Button(
                onClick = { viewModel.createTrack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar")
            }
        }
    }
}