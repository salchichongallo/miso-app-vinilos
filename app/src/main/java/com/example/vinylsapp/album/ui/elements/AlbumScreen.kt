package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vinylsapp.album.models.Album


@Composable
fun AlbumScreen(albums: List<Album>) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Álbumes", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        AlbumGrid(albums = albums)
    }
}