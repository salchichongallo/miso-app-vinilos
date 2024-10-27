package com.example.vinylsapp.login.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginOption(title: String, description: String, onSelect: () -> Unit) {
    ListItem(
        headlineContent = { Text(text = title) },
        trailingContent = {
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
            )
        },
        supportingContent = { Text(text = description) },
        modifier = Modifier.clickable(onClick = onSelect)
    )
}
