package com.example.vinylsapp.artist.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.models.buildAlbumDetailRoute

@Composable
fun ArtistAlbumItem(album: Album, navController: NavController) {
    ListItem(
        modifier = Modifier.clickable {
            val detailRoute = buildAlbumDetailRoute(album.id)
            navController.navigate(detailRoute) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        headlineContent = {
            Text(text = album.name)
        },
        trailingContent = {
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
        }
    )
}