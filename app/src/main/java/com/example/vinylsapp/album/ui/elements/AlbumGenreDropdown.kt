package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.vinylsapp.album.models.AlbumGenre

@ExperimentalMaterial3Api
@Composable
fun AlbumGenreDropdown(selectedGenre: AlbumGenre?, onGenreSelected: (AlbumGenre) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldWidth by remember { mutableIntStateOf(0) }
    val source = remember {
        MutableInteractionSource()
    }

    Box(
        modifier = Modifier
            .padding(bottom = 16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldWidth = coordinates.size.width
                },
            value = selectedGenre?.value ?: "Seleccione",
            onValueChange = {},
            readOnly = true,
            label = { Text("Género") },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Campo de género")
                }
            },
            interactionSource = source
        )
        if (source.collectIsPressedAsState().value)
            expanded = true


        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldWidth.toDp() }),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            AlbumGenre.entries.forEach { genre ->
                DropdownMenuItem(
                    onClick = {
                        onGenreSelected(genre)
                        expanded = false
                    },
                    text = { Text(genre.value) },
                )
            }
        }
    }
}


