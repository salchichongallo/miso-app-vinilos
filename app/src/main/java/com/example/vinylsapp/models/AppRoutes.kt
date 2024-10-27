package com.example.vinylsapp.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.Groups

enum class AppRoutes {
    Albums,
    Artists,
    Login,
}

val topLevelRoutes = listOf(
    TopLevelRoute("Álbumes", AppRoutes.Albums, Icons.Filled.Folder),
    TopLevelRoute("Artistas", AppRoutes.Artists, Icons.Outlined.Groups),
    TopLevelRoute("Login", AppRoutes.Login, Icons.AutoMirrored.Filled.DirectionsWalk),
)
