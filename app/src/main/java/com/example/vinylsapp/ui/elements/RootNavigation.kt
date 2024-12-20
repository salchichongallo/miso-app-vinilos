package com.example.vinylsapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.repositories.IAlbumRepository
import com.example.vinylsapp.album.tracks.repositories.ITrackRepository
import com.example.vinylsapp.album.tracks.ui.elements.TrackCreateScreen
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackCreateViewModel
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackListViewModel
import com.example.vinylsapp.album.ui.elements.AlbumCreateScreen
import com.example.vinylsapp.album.ui.elements.AlbumDetailScreen
import com.example.vinylsapp.album.ui.elements.AlbumListScreen
import com.example.vinylsapp.album.ui.viewmodels.AlbumCreateViewModel
import com.example.vinylsapp.album.ui.viewmodels.AlbumDetailViewModel
import com.example.vinylsapp.album.ui.viewmodels.AlbumListViewModel
import com.example.vinylsapp.artist.repositories.IArtistRepository
import com.example.vinylsapp.artist.ui.elements.ArtistAlbumScreen
import com.example.vinylsapp.artist.ui.elements.ArtistDetailScreen
import com.example.vinylsapp.artist.ui.elements.ArtistListScreen
import com.example.vinylsapp.artist.ui.viewmodels.ArtistAlbumViewModel
import com.example.vinylsapp.artist.ui.viewmodels.ArtistDetailViewModel
import com.example.vinylsapp.artist.ui.viewmodels.ArtistListViewModel
import com.example.vinylsapp.comment.repositories.CommentRepository
import com.example.vinylsapp.comment.repositories.ICommentRepository
import com.example.vinylsapp.comment.repositories.services.CommentRetrofitInstance
import com.example.vinylsapp.comment.ui.elements.CommentLitScreen
import com.example.vinylsapp.comment.ui.viewmodels.CommentCreateViewModel
import com.example.vinylsapp.comment.ui.viewmodels.CommentListViewModel
import com.example.vinylsapp.login.repositories.IUserRepository
import com.example.vinylsapp.login.repositories.UserRepository
import com.example.vinylsapp.login.ui.elements.LoginScreen
import com.example.vinylsapp.login.ui.viewmodels.UserViewModel
import com.example.vinylsapp.models.AppRoutes
import com.example.vinylsapp.ui.theme.VinylsAppTheme
import com.google.gson.Gson

@SuppressLint("RestrictedApi")
@Composable
fun RootNavigation(
    albumRepo: IAlbumRepository,
    trackRepository: ITrackRepository,
    artistRepository: IArtistRepository,
    commentRepository: ICommentRepository = CommentRepository(serviceAdapter = CommentRetrofitInstance.makeCommentService()),
    userRepository: IUserRepository = UserRepository(),
) {
    val navController = rememberNavController()
    val albumListViewModel = AlbumListViewModel(albumRepo)
    val artistListViewModel = ArtistListViewModel(artistRepo = artistRepository)
    val userViewModel = UserViewModel(userRepo = userRepository)

    VinylsAppTheme {
        NavHost(
            navController = navController,
            startDestination = AppRoutes.Login.value,
        ) {
            composable(route = AppRoutes.Albums.value) {
                AlbumListScreen(
                    viewModel = albumListViewModel,
                    navController = navController,
                    userViewModel = userViewModel,
                )
            }

            composable(route = AppRoutes.Artists.value) {
                ArtistListScreen(viewModel = artistListViewModel, navController = navController)
            }

            composable(route = AppRoutes.Login.value) {
                LoginScreen(navController, userViewModel = userViewModel)
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
                    userViewModel = userViewModel,
                )
            }

            composable(route = AppRoutes.TrackCreate.value) { navBackStackEntry ->
                val gson = Gson()
                val serializedAlbum = navBackStackEntry.arguments?.getString("album") ?: ""
                val album = gson.fromJson(serializedAlbum, Album::class.java)
                TrackCreateScreen(
                    viewModel = TrackCreateViewModel(
                        album = album,
                        trackRepo = trackRepository,
                    ),
                    navController,
                )
            }

            composable(route = AppRoutes.CommentList.value) { navBackStackEntry ->
                val albumIdInput = navBackStackEntry.arguments?.getString("id")
                val albumId = albumIdInput?.toIntOrNull()!!
                CommentLitScreen(
                    viewModel = CommentListViewModel(
                        commentRepo = commentRepository,
                        albumId = albumId,
                    ),
                    commentCreateViewModel = CommentCreateViewModel(
                        albumId = albumId,
                        commentRepo = commentRepository
                    ),
                    navController
                )
            }

            composable(route = AppRoutes.ArtistDetail.value) {
                val artistId = it.arguments?.getString("artistId")?.toInt()!!
                ArtistDetailScreen(
                    userViewModel = userViewModel,
                    viewModel = ArtistDetailViewModel(artistId, artistRepository),
                    navController = navController,
                )
            }

            composable(
                route = AppRoutes.AlbumCreate.value,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(300, easing = EaseIn)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(300, easing = EaseIn)
                    )
                }
            ) {
                AlbumCreateScreen(
                    viewModel = AlbumCreateViewModel(
                        albumRepository = albumRepo
                    ),
                    navController = navController
                )
            }

            composable(route = AppRoutes.ArtistAlbum.value) {
                val artistId = it.arguments?.getString("artistId")?.toInt()!!
                ArtistAlbumScreen(
                    viewModel = ArtistAlbumViewModel(
                        artistRepo = artistRepository,
                        artistId = artistId,
                        albumRepo = albumRepo
                    ),
                    navController = navController
                )
            }
        }
    }
}
