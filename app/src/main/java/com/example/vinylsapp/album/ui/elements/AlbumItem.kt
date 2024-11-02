package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil3.compose.AsyncImage
import com.example.vinylsapp.R
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.models.buildAlbumDetailRoute

@Composable
fun AlbumItem(album: Album, navController: NavController, index: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                val detailRoute = buildAlbumDetailRoute(album.id)
                navController.navigate(detailRoute) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column {
            Box(modifier = Modifier.height(12.dp))
            AsyncImage(
                model = album.cover,
                error = painterResource(R.drawable.default_album_cover),
                contentDescription = album.name,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = album.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .width(100.dp)
                    .padding(top = 4.dp, bottom = 2.dp)
                    .testTag("albumListItem_title_$index"),
            )
            Text(
                text = album.genre.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Left,
                modifier = Modifier.testTag("albumListItem_genre_$index"),
            )
            Box(modifier = Modifier.height(12.dp))
        }

    }
}
