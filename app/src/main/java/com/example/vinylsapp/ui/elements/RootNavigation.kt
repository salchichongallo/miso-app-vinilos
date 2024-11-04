package com.example.vinylsapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vinylsapp.album.repositories.IAlbumRepository
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackListViewModel
import com.example.vinylsapp.album.ui.elements.AlbumDetailScreen
import com.example.vinylsapp.album.ui.elements.AlbumListScreen
import com.example.vinylsapp.album.ui.viewmodels.AlbumDetailViewModel
import com.example.vinylsapp.album.ui.viewmodels.AlbumListViewModel
import com.example.vinylsapp.artist.ui.elements.ArtistListScreen
import com.example.vinylsapp.login.ui.elements.LoginScreen
import com.example.vinylsapp.models.AppRoutes
import com.example.vinylsapp.ui.theme.VinylsAppTheme

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
                ArtistListScreen(navController = navController)
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
        }
    }
}
