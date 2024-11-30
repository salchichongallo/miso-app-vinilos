package com.example.vinylsapp.album.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumReleaseDateField(
    albumReleaseDateError: String?,
    onSelectedDate: (Date) -> Unit,
    value: Date? = null
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = value?.let { convertDateToString(it) } ?: "",
            onValueChange = { },
            label = { Text("Fecha de lanzamiento") },
            placeholder = { Text("DD/MM/AAAA") },
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            readOnly = true,
            isError = albumReleaseDateError != null,
            supportingText = {
                if (albumReleaseDateError != null) {
                    Text(
                        text = albumReleaseDateError,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false,
                    )
                }
            }
        }

        LaunchedEffect(datePickerState.selectedDateMillis) {
            if (datePickerState.selectedDateMillis != null) {
                val calendar = Calendar.getInstance().apply {
                    timeInMillis = datePickerState.selectedDateMillis!!
                    add(Calendar.DAY_OF_MONTH, 1) // Sumamos un día
                }
                val adjustedDate = calendar.time
                onSelectedDate(adjustedDate)
                showDatePicker = false
            }
        }

    }
}

fun convertDateToString(date: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = date.time
    return formatter.format(calendar.time)
}
