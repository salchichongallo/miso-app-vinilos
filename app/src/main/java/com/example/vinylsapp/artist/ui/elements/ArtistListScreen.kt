package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.vinylsapp.artist.ui.viewmodels.ArtistListViewModel
import com.example.vinylsapp.ui.elements.Loader
import com.example.vinylsapp.ui.elements.VinylsBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistListScreen(viewModel: ArtistListViewModel, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Artistas") })
        },
        bottomBar = { VinylsBottomAppBar(navController) }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            val artists by viewModel.artists.collectAsState()
            if (viewModel.loading) {
                Loader()
            } else if (viewModel.hasError || artists.isEmpty()) {
                EmptyArtists(onRetry = { viewModel.loadArtists() })
            } else {
                ArtistListItems(artists = artists, navController = navController)
            }
        }
    }
}
