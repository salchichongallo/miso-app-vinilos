package com.example.vinylsapp.login.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Text("Estamos en login")
        }
    }
}
