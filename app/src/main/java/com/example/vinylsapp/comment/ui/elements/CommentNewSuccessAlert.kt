package com.example.vinylsapp.comment.ui.elements

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun CommentNewSuccessAlert(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Comentario enviado") },
        text = { Text("El comentario fue enviado exitosamente.") },
        confirmButton = {
            TextButton(onClick = onDismiss, modifier = Modifier.testTag("DismissButton")) {
                Text("Listo")
            }
        }
    )
}