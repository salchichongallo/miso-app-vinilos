package com.example.vinylsapp.album.tracks.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackCreateViewModel

@Composable
fun TrackNewForm(viewModel: TrackCreateViewModel, navController: NavController) {
    Column {
        OutlinedTextField(
            value = viewModel.trackName,
            onValueChange = { viewModel.onTrackNameChange(it) },
            label = { Text("Nombre") },
            placeholder = { Text("Ingrese el nombre del track") },
            isError = viewModel.trackNameErrorMessage.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("NameTrackTextField")
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        viewModel.isTrackNameTouched = true
                    }
                },
            supportingText = {
                if (viewModel.isTrackNameTouched && viewModel.trackNameErrorMessage.isNotEmpty()) {
                    Text(
                        text = viewModel.trackNameErrorMessage,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )

        Box(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.trackDuration,
            onValueChange = { newValue ->
                val digitsOnly = newValue.text.filter { it.isDigit() }
                val formattedValue = when {
                    digitsOnly.length >= 3 -> "${digitsOnly.take(2)}:${
                        digitsOnly.drop(2).take(2)
                    }"

                    digitsOnly.isNotEmpty() -> digitsOnly
                    else -> ""
                }

                viewModel.onTrackDurationChange(formattedValue)

                val newCursorPosition =
                    if (digitsOnly.length == 2) 3 else formattedValue.length
                viewModel.trackDuration = TextFieldValue(
                    text = formattedValue,
                    selection = TextRange(newCursorPosition)
                )
            },
            label = { Text("Duración") },
            placeholder = { Text("MM:SS") },
            isError = viewModel.trackDurationErrorMessage.isNotEmpty(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("DurationTrackTextField")
                .padding(start = 0.dp, top = 2.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        viewModel.isTrackDurationTouched = true
                    }
                },
            supportingText = {
                if (viewModel.isTrackDurationTouched && viewModel.trackDurationErrorMessage.isNotEmpty()) {
                    Text(
                        text = viewModel.trackDurationErrorMessage,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )

        Box(modifier = Modifier.height(16.dp))

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
                    .testTag("CancelTrackButton")
            ) {
                Text("Cancelar y volver")
            }

            Button(
                onClick = { viewModel.createTrack() },
                modifier = Modifier
                    .weight(1f)
                    .testTag("CreateTrackButton"),
                enabled = viewModel.isTrackNameValid && viewModel.isTrackDurationValid
            ) {
                Text("Agregar")
            }
        }
    }
}
