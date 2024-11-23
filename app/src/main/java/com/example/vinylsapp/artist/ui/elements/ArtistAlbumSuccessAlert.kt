package com.example.vinylsapp.artist.ui.elements

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun ArtistAlbumSuccessAlert(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Álbum asociado") },
        text = { Text("Álbum asociado correctamente.") },
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