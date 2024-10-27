package com.example.vinylsapp.album.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import com.example.vinylsapp.ui.theme.VinylsAppTheme

// TODO: Eliminar activity
class AlbumListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VinylsAppTheme {
                Text("AlbumListActivity")
            }
        }
    }
}

