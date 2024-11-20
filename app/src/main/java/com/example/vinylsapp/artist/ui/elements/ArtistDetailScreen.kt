package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.ui.elements.VinylsBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetailScreen(navController: NavController, artist: Artist) {
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