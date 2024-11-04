package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
            if (viewModel.artists.isNotEmpty()) {
                ArtistListItems(artists = viewModel.artists)
            } else if (viewModel.loading) {
                Loader()
            }
        }
    }
}
