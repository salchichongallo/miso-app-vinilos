package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.album.tracks.ui.elements.AlbumTrackList
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackListViewModel
import com.example.vinylsapp.album.ui.viewmodels.AlbumDetailViewModel
import com.example.vinylsapp.ui.elements.VinylsBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    viewModel: AlbumDetailViewModel,
    tracksViewModel: TrackListViewModel,
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("AlbumDetailScreen"),
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        },
        bottomBar = { VinylsBottomAppBar(navController) }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            if (viewModel.album != null) {
                Column {
                    AlbumCover(album = viewModel.album!!, modifier = Modifier.fillMaxWidth())
                    Box(modifier = Modifier.height(24.dp))
                    AlbumTrackList(tracks = tracksViewModel.tracks)
                }
            }
        }
    }
}
