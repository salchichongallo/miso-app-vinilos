package com.example.vinylsapp.album.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.vinylsapp.album.ui.elements.AlbumScreen
import com.example.vinylsapp.album.ui.viewmodels.AlbumListViewModel
import com.example.vinylsapp.ui.theme.VinylsAppTheme

class AlbumListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VinylsAppTheme {
                Surface {
                    AlbumScreen(viewModel = AlbumListViewModel())
                }
            }
        }
    }
}

