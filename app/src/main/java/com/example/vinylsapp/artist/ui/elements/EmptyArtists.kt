package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.R

@Composable
fun EmptyArtists(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.music_placeholder),
            contentDescription = null,
        )
        Box(modifier = Modifier.height(24.dp))
        Text(
            "No hay artistas disponibles",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
        Box(modifier = Modifier.height(24.dp))
        Button(onClick = onRetry) {
            Text("Intentar de nuevo")
        }
    }
}
