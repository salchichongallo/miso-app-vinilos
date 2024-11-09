package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.artist.models.Artist

@Composable
fun ArtistListItems(artists: List<Artist>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(artists.size) { index ->
            ArtistPreviewItem(artist = artists.get(index))
        }
    }
}
