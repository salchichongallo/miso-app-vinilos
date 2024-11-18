package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.album.ui.viewmodels.AlbumListViewModel
import com.example.vinylsapp.login.ui.viewmodels.UserViewModel
import com.example.vinylsapp.ui.elements.VinylsBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumListScreen(viewModel: AlbumListViewModel, navController: NavController, userViewModel: UserViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Álbumes") })
        },
        bottomBar = { VinylsBottomAppBar(navController) },
        floatingActionButton = {
            if (userViewModel.isCollector) {
                ExtendedFloatingActionButton(
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Comentar"
                        )
                    },
                    text = { Text(text = "Álbum") },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                )
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            AlbumGrid(albums = viewModel.albums, navController = navController)
        }
    }
}
