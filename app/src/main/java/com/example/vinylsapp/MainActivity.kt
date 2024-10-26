package com.example.vinylsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.example.vinylsapp.album.models.Album
import com.example.vinylsapp.data.remote.RetrofitServiceFactory
import com.example.vinylsapp.ui.screens.AlbumScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = RetrofitServiceFactory.makeAlbumService()
        var albums by mutableStateOf(listOf<Album>())

        lifecycleScope.launch {
            try {
                val result = service.getAlbums()
                albums = result
                println(albums)
            } catch (e: Exception) {
                albums = listOf()
                println("Error al cargar datos: ${e.message}")
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
