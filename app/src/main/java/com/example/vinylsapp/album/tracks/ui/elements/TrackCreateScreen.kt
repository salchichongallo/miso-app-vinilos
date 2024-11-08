package com.example.vinylsapp.album.tracks.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
    val trackName = viewModel.track.name
    val trackDuration = viewModel.track.duration
    val album = viewModel.album


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
                    painter = painterResource(id = R.drawable.undraw_music),
                    contentDescription = "Ilustración de canción",
                    modifier = Modifier.size(250.dp)
                )
                if (album != null) {
                    Text("Agregar el track al álbum ${album.name}")
                }

                OutlinedTextField(
                    value = trackName,
                    onValueChange = { viewModel.onTrackNameChange(it) },
                    label = { Text("Nombre") },
                    placeholder = { Text("Ingrese el nombre del track") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                )

                OutlinedTextField(
                    value = trackDuration,
                    onValueChange = { viewModel.onTrackDurationChange(it) },
                    label = { Text("Duración") },
                    placeholder = { Text("MM:SS") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancelar y volver")
                    }

                    Button(
                        onClick = { viewModel.createTrack() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Agregar")
                    }
                }
            }
        }
    }
}
