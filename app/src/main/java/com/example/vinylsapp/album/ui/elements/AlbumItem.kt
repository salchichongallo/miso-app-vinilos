package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.vinylsapp.R
import com.example.vinylsapp.album.models.Album

@Composable
fun AlbumItem(album: Album) {
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = album.cover,
            error = painterResource(R.drawable.default_album_cover),
            contentDescription = album.name,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = album.name, maxLines = 1, overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .width(100.dp)
                .padding(top = 4.dp, bottom = 2.dp),
        )
        Text(text = album.genre.value, style = MaterialTheme.typography.bodySmall)
        Box(modifier = Modifier.height(24.dp))
    }
}
