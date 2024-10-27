package com.example.vinylsapp.login.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vinylsapp.models.AppRoutes

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                LoginHero()
                Box(modifier = Modifier.height(24.dp))
                LoginOption(
                    title = "Ingresar como invitado",
                    description = "Puedes explorar los álbumes y artistas.",
                    onSelect = {
                        navController.navigate(AppRoutes.Albums.value) {
                            // Delete previous route from stack
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            // Avoid duplicate instances in the stack
                            launchSingleTop = true
                        }
                    }
                )
                Divider()
                Box(modifier = Modifier.height(16.dp))
                LoginOption(
                    title = "Ingresar como coleccionista",
                    description = "Agrega álbumes, comentarios y tracks.",
                    onSelect = {
                        navController.navigate(AppRoutes.Albums.value) {
                            // Delete previous route from stack
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            // Avoid duplicate instances in the stack
                            launchSingleTop = true
                        }
                    }
                )
                Divider()
            }
        }
    }
}
