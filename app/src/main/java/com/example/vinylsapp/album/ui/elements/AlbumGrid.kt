package com.example.vinylsapp.album.ui.elements


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.album.models.Album

@Composable
fun AlbumGrid(albums: List<Album>) {
    if (albums.isEmpty()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "No se encontraron coincidencias",
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp),
        ) {
            items(albums.size) { index ->
                AlbumItem(album = albums[index])
            }
        }
    }
}
