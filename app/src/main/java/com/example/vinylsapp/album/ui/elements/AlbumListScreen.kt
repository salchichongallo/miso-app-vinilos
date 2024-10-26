package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vinylsapp.album.ui.viewmodels.AlbumListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumListScreen(viewModel: AlbumListViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Álbumes") })
        }) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            AlbumGrid(albums = viewModel.albums)
        }
    }
}
