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
import com.example.vinylsapp.album.ui.elements.AlbumListScreen
import com.example.vinylsapp.album.ui.viewmodels.AlbumListViewModel
import com.example.vinylsapp.models.AppRoutes
import com.example.vinylsapp.ui.theme.VinylsAppTheme

@SuppressLint("RestrictedApi")
@Composable
fun RootNavigation() {
    val navController = rememberNavController()
    val albumListViewModel = AlbumListViewModel()
    VinylsAppTheme {
        NavHost(
            navController = navController,
            startDestination = AppRoutes.Albums.name,
        ) {
            composable(route = AppRoutes.Albums.name) {
                AlbumListScreen(viewModel = albumListViewModel, navController = navController)
            }

            composable(route = AppRoutes.Artists.name) {
                // TODO: Implement artist list screen
                Scaffold(
                    bottomBar = { VinylsBottomAppBar(navController) }
                ) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        Text("Estamos en artistas")
                    }
                }
            }

            composable(route = AppRoutes.Login.name) {
                // TODO: Implement login screen
                Scaffold { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        Text("Estamos en login")
                    }
                }
            }
        }
    }
}
