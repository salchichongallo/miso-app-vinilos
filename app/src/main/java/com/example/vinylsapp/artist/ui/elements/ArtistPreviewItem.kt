package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.vinylsapp.artist.models.Artist
import com.example.vinylsapp.models.buildArtistDetailRoute

@Composable
fun ArtistPreviewItem(artist: Artist, navController: NavController) {
    ListItem(
        modifier = Modifier
            .testTag("artistItem")
            .clickable {
                val artistDetailRoute = buildArtistDetailRoute(artist)
                navController.navigate(artistDetailRoute) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        leadingContent = {
            ArtistImage(url = artist.image, modifier = Modifier.size(56.dp))
        },
        headlineContent = {
            Text(
                text = artist.name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        supportingContent = {
            Text(
                text = artist.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        trailingContent = {
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
        }
    )
}
