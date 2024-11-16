package com.example.vinylsapp.album.tracks.ui.elements

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun TrackNewSuccessAlert(album: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Track asociado") },
        text = { Text("El track ha sido asociado al álbum $album") },
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
