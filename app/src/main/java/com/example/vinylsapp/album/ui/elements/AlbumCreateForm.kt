package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AlbumCreateForm(navController: NavController) {
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Cancelar y volver")
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Crear")
            }
        }
    }
}