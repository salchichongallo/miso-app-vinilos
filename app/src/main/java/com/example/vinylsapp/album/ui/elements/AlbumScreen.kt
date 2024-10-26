package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.album.ui.viewmodels.AlbumListViewModel


@Composable
fun AlbumScreen(viewModel: AlbumListViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Álbumes",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 50.dp, start = 16.dp, bottom = 21.dp),
            textAlign = TextAlign.Left
        )
        AlbumGrid(albums = viewModel.albums)

    }
}
