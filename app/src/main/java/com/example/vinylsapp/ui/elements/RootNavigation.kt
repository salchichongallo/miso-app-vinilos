package com.example.vinylsapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.repositories.IAlbumRepository
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository
import com.example.vinylsapp.album.tracks.ui.elements.TrackCreateScreen
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackCreateViewModel
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackListViewModel
import com.example.vinylsapp.album.ui.elements.AlbumDetailScreen
import com.example.vinylsapp.album.ui.elements.AlbumListScreen
import com.example.vinylsapp.album.ui.viewmodels.AlbumDetailViewModel
import com.example.vinylsapp.album.ui.viewmodels.AlbumListViewModel
import com.example.vinylsapp.login.ui.elements.LoginScreen
import com.example.vinylsapp.models.AppRoutes
import com.example.vinylsapp.ui.theme.VinylsAppTheme
import kotlinx.serialization.json.Json

@SuppressLint("RestrictedApi")
@Composable
fun RootNavigation(albumRepo: IAlbumRepository, trackRepository: ITrackRepository) {
    val navController = rememberNavController()
    val albumListViewModel = AlbumListViewModel(albumRepo)

    VinylsAppTheme {
        NavHost(
            navController = navController,
            startDestination = AppRoutes.Login.value,
        ) {
            composable(route = AppRoutes.Albums.value) {
                AlbumListScreen(viewModel = albumListViewModel, navController = navController)
            }

            composable(route = AppRoutes.Artists.value) {
                // TODO: Implement artist list screen
                Scaffold(
                    bottomBar = { VinylsBottomAppBar(navController) }
                ) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        Text("Estamos en artistas")
                    }
                }
            }

            composable(route = AppRoutes.Login.value) {
                LoginScreen(navController)
            }

            composable(route = AppRoutes.AlbumDetail.value) { navBackStackEntry ->
                val albumIdInput = navBackStackEntry.arguments?.getString("id")
                val albumId = albumIdInput?.toIntOrNull()!!
                AlbumDetailScreen(
                    viewModel = AlbumDetailViewModel(albumId = albumId, albumRepo = albumRepo),
                    tracksViewModel = TrackListViewModel(
                        albumId = albumId,
                        trackRepo = trackRepository,
                    ),
                    navController = navController,
                )
            }

            composable(route = AppRoutes.TrackCreate.value) { navBackStackEntry ->
                val serializedAlbum = navBackStackEntry.arguments?.getString("album") ?: ""
                val album = Json.decodeFromString<Album>(serializedAlbum)
                TrackCreateScreen(
                    viewModel = TrackCreateViewModel(
                        albumData = album,
                        trackRepo = trackRepository,
                    ),
                    navController,
                )
            }
        }
    }
}
