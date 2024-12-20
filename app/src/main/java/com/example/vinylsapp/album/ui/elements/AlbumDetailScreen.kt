package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.album.tracks.ui.elements.AlbumTrackList
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackListViewModel
import com.example.vinylsapp.album.ui.viewmodels.AlbumDetailViewModel
import com.example.vinylsapp.login.ui.viewmodels.UserViewModel
import com.example.vinylsapp.models.buildCommentListScreenRoute
import com.example.vinylsapp.models.buildTrackNewScreenRoute
import com.example.vinylsapp.ui.elements.VinylsBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    viewModel: AlbumDetailViewModel,
    tracksViewModel: TrackListViewModel,
    navController: NavController,
    userViewModel: UserViewModel,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("AlbumDetailScreen"),
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                actions = {
                    if (userViewModel.isCollector) {
                        IconButton(modifier = Modifier.testTag("CommentsButton"), onClick = {
                            val commentListScreenRoute =
                                buildCommentListScreenRoute(viewModel.album!!.id)
                            navController.navigate(commentListScreenRoute)
                        }) {
                            Icon(Icons.Outlined.ChatBubble, contentDescription = "Comentarios")
                        }
                    }
                }
            )
        },
        bottomBar = { VinylsBottomAppBar(navController) },
        floatingActionButton = {
            if (userViewModel.isCollector) {
                FloatingActionButton(
                    onClick = {
                        val trackNewRoute = buildTrackNewScreenRoute(viewModel.album!!)
                        navController.navigate(trackNewRoute)
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .testTag("CreateTrackButton"),
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Agregar Canción",
                        )
                        Text("Track")
                    }
                }
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            if (viewModel.album != null) {
                Column {
                    AlbumCover(album = viewModel.album!!, modifier = Modifier.fillMaxWidth())
                    Box(modifier = Modifier.height(24.dp))
                    AlbumTrackList(tracks = tracksViewModel.tracks)
                }
            }
        }
    }
}
