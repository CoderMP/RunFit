package com.codermp.runfit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

/**
 * Composable that defines the navigation graph for the app.
 * @param navController The navigation controller to use.
 */
@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "" // TODO: Replace with proper start destination.
    ) {
        /**
         * TODO: populate with navigation sub-graphs
         */
    }
}