package com.example.vinylsapp.album.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.album.repositories.services.RetrofitServiceFactory
import com.example.vinylsapp.album.ui.elements.AlbumScreen
import kotlinx.coroutines.launch

class AlbumListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val service = RetrofitServiceFactory.makeAlbumService()
        var albums by mutableStateOf(listOf<Album>())

        lifecycleScope.launch {
            try {
                val result = service.fetchAlbums()
                albums = result
            } catch (e: Exception) {
                albums = listOf()
            }

            setContent {
                MaterialTheme {
                    Surface {
                        AlbumScreen(albums = albums)
                    }
                }
            }
        }
    }
}

