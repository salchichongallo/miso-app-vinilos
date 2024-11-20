package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.artist.ui.viewmodels.ArtistDetailViewModel
import com.example.vinylsapp.ui.elements.Loader
import com.example.vinylsapp.ui.elements.VinylsBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetailScreen(viewModel: ArtistDetailViewModel, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Artista") },
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
            if (viewModel.loading) {
                Loader()
            }
            if (viewModel.artist != null) {
                val artist = viewModel.artist!!
                Column {
                    ArtistDetailHero(artist = artist)
                    ArtistDetailDescription(description = artist.description)
                    Spacer(Modifier.height(8.dp))
                    SectionHeader(title = "Álbumes")
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(artist.albums.size) { index ->
                            ArtistAlbumItem(
                                album = artist.albums[index],
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
