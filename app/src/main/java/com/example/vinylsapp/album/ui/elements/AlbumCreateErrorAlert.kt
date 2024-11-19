package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun AlbumCreateErrorAlert(onDismiss: () -> Unit) {
    AlertDialog(
        text = { Text("Ha ocurrido un error en el servidor, por favor intente nuevamente") },
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Error del servidor")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Rounded.Error,
                    contentDescription = "Error Icon",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        onDismissRequest = {},
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.testTag("CloseErrorAlertButton")
            ) {
                Text("Listo")
            }
        }
    )
}