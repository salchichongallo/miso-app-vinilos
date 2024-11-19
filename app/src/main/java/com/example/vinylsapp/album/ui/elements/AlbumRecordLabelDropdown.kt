package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.example.vinylsapp.album.models.AlbumRecordLabel

@ExperimentalMaterial3Api
@Composable
fun AlbumRecordLabelDropdown(selectedLabel: AlbumRecordLabel?, onLabelSelected: (AlbumRecordLabel) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldWidth by remember { mutableIntStateOf(0) }

    Box {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldWidth = coordinates.size.width
                },
            value = selectedLabel?.value ?: "Seleccione",
            onValueChange = {},
            readOnly = true,
            label = { Text("Sello discográfico") },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Campo de sello discográfico")
                }
            },
        )

        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldWidth.toDp() }),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            AlbumRecordLabel.entries.forEach { recordLabel ->
                DropdownMenuItem(
                    onClick = {
                        onLabelSelected(recordLabel)
                        expanded = false
                    },
                    text = { Text(recordLabel.value) },
                )
            }
        }
    }
}