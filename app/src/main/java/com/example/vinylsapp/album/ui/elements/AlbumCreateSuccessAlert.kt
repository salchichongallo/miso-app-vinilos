package com.example.vinylsapp.album.ui.elements

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController

@Composable
fun AlbumCreateSuccessAlert(navController: NavController, onDismiss: () -> Unit) {
    AlertDialog(
        text = { Text("El álbum ha sido creado con éxito") },
        title = { Text("Álbum creado") },
        onDismissRequest = {
            navController.popBackStack()
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.testTag("CloseSuccessAlertButton")
            ) {
                Text("Listo")
            }
        }
    )
}