package com.example.vinylsapp.album.tracks.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.album.tracks.models.Track

@Composable
fun AlbumTrackList(tracks: List<Track>) {
    Column {
        Text(
            text = "Canciones",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
        )
        if (tracks.isEmpty()) {
            Text(
                "No existen canciones asociadas álbum",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                style = MaterialTheme.typography.titleMedium,

                )
        } else {
            LazyColumn {
                items(tracks) {
                    TrackItem(track = it)
                    Box(modifier = Modifier.height(12.dp))
                }
            }
        }

    }
}
