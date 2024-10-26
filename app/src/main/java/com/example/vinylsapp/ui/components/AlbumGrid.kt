package com.example.vinylsapp.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.data.model.Album

@Composable
fun AlbumGrid(albums: List<Album>) {
    if (albums.isEmpty()) {
        Text("No se encontraron coincidencias", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(albums.size) { index ->
                AlbumItem(album = albums[index])
            }
        }
    }
}