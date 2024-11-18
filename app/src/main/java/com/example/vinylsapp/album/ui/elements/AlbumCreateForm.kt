package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AlbumCreateForm() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = "",
            onValueChange = {},
            label = { Text("Nombre") },
            placeholder = { Text("Ingrese el nombre del álbum") },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = "",
            onValueChange = {},
            label = { Text("cover") },
            placeholder = { Text("Ingrese el cover del álbum") },
        )
        AlbumReleaseDateField()
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = "",
            onValueChange = {  },
            placeholder = { Text("Ingrese la descripción del álbum") },
            label = { Text("Descripción") },
            maxLines = 5,
            minLines = 5,
        )
    }
}