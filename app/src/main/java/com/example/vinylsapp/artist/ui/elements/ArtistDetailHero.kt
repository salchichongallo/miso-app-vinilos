package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.artist.models.Artist

@Composable
fun ArtistDetailHero(artist: Artist) {
    Box(contentAlignment = Alignment.BottomStart) {
        ArtistImage(
            url = artist.image,
            modifier = Modifier
                .fillMaxWidth()
                .height(296.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                    )
                )
        ) {
            Text(
                text = artist.name,
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
            )
            Spacer(Modifier.height(4.dp))

            val foundedAt = artist.birthDate.slice(0..3)
            Text(
                text = "Formada en $foundedAt",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
            )
        }
    }
}
