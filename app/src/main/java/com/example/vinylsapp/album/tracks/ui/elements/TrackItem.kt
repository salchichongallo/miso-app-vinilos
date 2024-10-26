package com.example.vinylsapp.album.tracks.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.album.tracks.models.Track

@Composable
fun TrackItem(track: Track) {
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.inversePrimary),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                Icons.Filled.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
            )
        }
        Box(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = track.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(text = track.duration)
        }
    }
}
