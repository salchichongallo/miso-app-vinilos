package com.example.vinylsapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vinylsapp.models.topLevelRoutes

@SuppressLint("RestrictedApi")
@Composable
fun VinylsBottomAppBar(navController: NavController) {
    BottomAppBar {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            topLevelRoutes.forEach { topLevelRoute ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.hasRoute(topLevelRoute.route.value, arguments = null)
                } == true
                NavigationBarItem(
                    icon = {
                        Icon(topLevelRoute.icon, contentDescription = null)
                    },
                    selected = isSelected,
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
