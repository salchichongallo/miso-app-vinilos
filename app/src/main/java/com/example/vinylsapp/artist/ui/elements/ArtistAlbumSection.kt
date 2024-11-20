package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.album.models.Album

@Composable
fun ArtistAlbumSection(albums: List<Album>, navController: NavController) {
    Column {
        SectionHeader(title = "Álbumes")
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(albums.size) { index ->
                ArtistAlbumItem(
                    album = albums[index],
                    navController = navController,
                )
            }
        }
    }
}
