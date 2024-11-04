package com.example.vinylsapp.artist.ui.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.vinylsapp.R

@Composable
fun ArtistImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        error = painterResource(R.drawable.default_album_cover),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}
