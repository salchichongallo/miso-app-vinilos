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
    val trackName = viewModel.track.name
    val trackDuration = viewModel.track.duration
    val album = viewModel.album

    val trackDurationState = remember { mutableStateOf(TextFieldValue(trackDuration)) }

    val isTrackNameTouched = remember { mutableStateOf(false) }
    val isTrackNameValid = remember { mutableStateOf(false) }
    val trackNameErrorMessage = remember { mutableStateOf("") }

    val isTrackDurationTouched = remember { mutableStateOf(false) }
    val isTrackDurationValid = remember { mutableStateOf(false) }
    val trackDurationErrorMessage = remember { mutableStateOf("") }

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
                    painter = painterResource(id = R.drawable.undraw_music),
                    contentDescription = "Ilustración de canción",
                    modifier = Modifier.size(250.dp)
                )
                if (album != null) {
                    Text("Agregar el track al álbum ${album.name}")
                }

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = trackName,
                        onValueChange = {
                            viewModel.onTrackNameChange(it)
                            isTrackNameTouched.value = true
                            trackNameErrorMessage.value = if (it.isEmpty()) {
                                "El nombre es requerido"
                            } else if (it.length !in 10..30) {
                                "El nombre debe tener entre 10 y 30 caracteres"
                            } else {
                                ""
                            }
                            isTrackNameValid.value = it.isNotEmpty() && it.length in 10..30
                        },
                        label = { Text("Nombre") },
                        placeholder = { Text("Ingrese el nombre del track") },
                        isError = trackNameErrorMessage.value.isNotEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged { focusState ->
                                if (!focusState.isFocused) {
                                    isTrackNameTouched.value = true
                                }
                            }
                    )
                    if (isTrackNameTouched.value && trackNameErrorMessage.value.isNotEmpty()) {
                        Text(
                            text = trackNameErrorMessage.value,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.Start)
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = trackDurationState.value,
                        onValueChange = { newValue ->
                            val digitsOnly = newValue.text.filter { it.isDigit() }
                            val formattedValue = when {
                                digitsOnly.length >= 3 -> "${digitsOnly.take(2)}:${digitsOnly.drop(2).take(2)}"
                                digitsOnly.length >= 1 -> digitsOnly
                                else -> ""
                            }

                            viewModel.onTrackDurationChange(formattedValue)

                            val newCursorPosition = if (digitsOnly.length == 2) 3 else formattedValue.length
                            trackDurationState.value = TextFieldValue(
                                text = formattedValue,
                                selection = TextRange(newCursorPosition)
                            )

                            val regex = "^([0-5][0-9]):([0-5][0-9])$".toRegex()
                            if (formattedValue.isEmpty()) {
                                trackDurationErrorMessage.value = "La duración es requerida"
                            } else if (!formattedValue.matches(regex)) {
                                isTrackDurationValid.value = false
                                trackDurationErrorMessage.value = "Duración inválida. El formato debe ser MM:SS (00:30 - 59:59)"
                            } else {
                                isTrackDurationValid.value = true
                                trackDurationErrorMessage.value = ""
                            }
                        },
                        label = { Text("Duración") },
                        placeholder = { Text("MM:SS") },
                        isError = trackDurationErrorMessage.value.isNotEmpty(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 0.dp, top = 2.dp)
                            .onFocusChanged { focusState ->
                                if (!focusState.isFocused) {
                                    isTrackDurationTouched.value = true
                                }
                            }
                    )
                    if (isTrackDurationTouched.value && trackDurationErrorMessage.value.isNotEmpty()) {
                        Text(
                            text = trackDurationErrorMessage.value,
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
                        enabled = isTrackNameValid.value && isTrackDurationValid.value
                    ) {
                        Text("Agregar")
                    }

                    if (viewModel.isSuccessModalVisible) {
                        AlertDialog(
                            onDismissRequest = { viewModel.isSuccessModalVisible = false },
                            title = {
                                Text("Track asociado")
                            },
                            text = { Text("El track ha sido asociado al álbum ${album!!.name}") },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        viewModel.isSuccessModalVisible = false
                                    }
                                ) {
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
}
