package com.example.vinylsapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vinylsapp.ui.theme.VinylsAppTheme

enum class AppRoutes {
    Albums,
    Artists,
    Login,
}

data class TopLevelRoute(val title: String, val route: AppRoutes, val icon: ImageVector)

@SuppressLint("RestrictedApi")
@Composable
fun RootNavigation() {
    val topLevelRoutes = listOf(
        TopLevelRoute("Álbumes", AppRoutes.Albums, Icons.Filled.Folder),
        TopLevelRoute("Artistas", AppRoutes.Artists, Icons.Outlined.Groups),
        TopLevelRoute("Login", AppRoutes.Login, Icons.AutoMirrored.Filled.DirectionsWalk),
    )

    VinylsAppTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    NavigationBar {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        topLevelRoutes.forEach { topLevelRoute ->
                            NavigationBarItem(
                                icon = {
                                    Icon(topLevelRoute.icon, contentDescription = null)
                                },
                                selected = currentDestination?.hierarchy?.any {
                                    it.hasRoute(topLevelRoute.route.name, arguments = null)
                                } == true,
                                label = { Text(text = topLevelRoute.title) },
                                onClick = {
                                    navController.navigate(topLevelRoute.route.name) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppRoutes.Albums.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = AppRoutes.Albums.name) {
                    Text("Estamos en álbumes")
                }

                composable(route = AppRoutes.Artists.name) {
                    Text("Estamos en artistas")
                }

                composable(route = AppRoutes.Login.name) {
                    Text(text = "Estamos en login")
                }
            }
        }
    }
}
