package com.example.vinylsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.vinylsapp.album.ui.AlbumListActivity
import com.example.vinylsapp.ui.theme.VinylsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VinylsAppTheme {
                Surface {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Button(
                            onClick = {
                                Intent(applicationContext, AlbumListActivity::class.java)
                                    .also { startActivity(it) }
                            }
                        ) {
                            Text(text = "Álbumes")
                        }
                    }
                }
            }
        }
    }
}
