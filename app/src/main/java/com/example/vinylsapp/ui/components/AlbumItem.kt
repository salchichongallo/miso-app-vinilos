package com.example.vinylsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.vinylsapp.data.model.Album

@Composable
fun AlbumItem(album: Album) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(100.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = album.cover),
            contentDescription = album.name,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = album.name, maxLines = 1, overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall)
        Text(text = album.genre, style = MaterialTheme.typography.bodySmall)
    }
}