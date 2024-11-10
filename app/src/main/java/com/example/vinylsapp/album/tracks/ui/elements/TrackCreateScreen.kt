package com.example.vinylsapp.album.tracks.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vinylsapp.R
import com.example.vinylsapp.album.tracks.ui.viewmodels.TrackCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackCreateScreen(viewModel: TrackCreateViewModel, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar track") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        },
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.music_placeholder),
                    contentDescription = "Ilustración de canción",
                    modifier = Modifier.size(250.dp)
                )
                Text("Agregar el track al álbum ${viewModel.album.name}")

                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = viewModel.track.name,
                        onValueChange = { viewModel.onTrackNameChange(it) },
                        label = { Text("Nombre") },
                        placeholder = { Text("Ingrese el nombre del track") },
                        isError = viewModel.trackNameErrorMessage.isNotEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged { focusState ->
                                if (!focusState.isFocused) {
                                    viewModel.isTrackNameTouched = true
                                }
                            }
                    )
                    if (viewModel.isTrackNameTouched && viewModel.trackNameErrorMessage.isNotEmpty()) {
                        Text(
                            text = viewModel.trackNameErrorMessage,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.Start)
                        )
                    }
                }

                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = viewModel.trackDurationState,
                        onValueChange = { newValue ->
                            val digitsOnly = newValue.text.filter { it.isDigit() }
                            val formattedValue = when {
                                digitsOnly.length >= 3 -> "${digitsOnly.take(2)}:${digitsOnly.drop(2).take(2)}"
                                digitsOnly.length >= 1 -> digitsOnly
                                else -> ""
                            }

                            viewModel.onTrackDurationChange(formattedValue)

                            val newCursorPosition = if (digitsOnly.length == 2) 3 else formattedValue.length
                            viewModel.trackDurationState = TextFieldValue(
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
                            .padding(start = 0.dp, top = 2.dp)
                            .onFocusChanged { focusState ->
                                if (!focusState.isFocused) {
                                    viewModel.isTrackDurationTouched = true
                                }
                            }
                    )
                    if (viewModel.isTrackDurationTouched && viewModel.trackDurationErrorMessage.isNotEmpty()) {
                        Text(
                            text = viewModel.trackDurationErrorMessage,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(start = 0.dp, top = 2.dp)
                                .align(alignment = Alignment.Start)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancelar y volver")
                    }

                    Button(
                        onClick = { viewModel.createTrack() },
                        modifier = Modifier.weight(1f),
                        enabled = viewModel.isTrackNameValid && viewModel.isTrackDurationValid
                    ) {
                        Text("Agregar")
                    }
                }

                if (viewModel.isSuccessModalVisible) {
                    AlertDialog(
                        onDismissRequest = { viewModel.isSuccessModalVisible = false },
                        title = { Text("Track asociado") },
                        text = { Text("El track ha sido asociado al álbum ${viewModel.album.name}") },
                        confirmButton = {
                            Button(onClick = { viewModel.isSuccessModalVisible = false }) {
                                Text("Aceptar")
                            }
                        }
                    )
                }

                if (viewModel.isErrorModalVisible) {
                    AlertDialog(
                        onDismissRequest = { viewModel.isErrorModalVisible = false },
                        title = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Ha ocurrido un error")
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Rounded.Error,
                                    contentDescription = "Error Icon",
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        text = { Text("Ocurrió un error al agregar el track, intenta nuevamente por favor.") },
                        confirmButton = {
                            Button(onClick = { viewModel.isErrorModalVisible = false }) {
                                Text("Listo")
                            }
                        }
                    )
                }
            }
        }
    }
}
