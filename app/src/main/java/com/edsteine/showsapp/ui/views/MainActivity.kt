// File: Navigation.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Main activity of the Shows app, responsible for setting up the UI
//              and the navigation graph for the app. It includes a bottom navigation
//              bar and defines the destinations that can be used in a NavHost.
// Used Libraries: Android SDK, Jetpack Compose, Jetpack Navigation Component, Dagger Hilt for Dependency Injection, Timber for logging

package com.edsteine.showsapp.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edsteine.showsapp.ui.home.HomeScreen
import com.edsteine.showsapp.ui.screens.FavoritesScreen
import com.edsteine.showsapp.ui.screens.ProfileScreen
import com.edsteine.showsapp.ui.screens.SearchScreen
import com.edsteine.showsapp.ui.showsdetail.ShowsDetailsScreen
import com.edsteine.showsapp.ui.theme.ShowsTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Main activity of the Shows app, responsible for setting up the UI.
 * Date Updated: 2024-10-15
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowsTheme {
                ShowsAppScreen()
            }
        }
    }
}

/**
 * Composable function to set up the app's main screen.
 * It includes a bottom navigation bar and the navigation graph.
 */
@Composable
fun ShowsAppScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

/**
 * Composable function to define the navigation graph of the app.
 */
@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Screen.Home.route, modifier = modifier) {
        AppNavigation(navController)
    }
}

/**
 * Sealed class representing the different screens in the app.
 */
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", androidx.compose.material.icons.Icons.Filled.Home)
    object Search : Screen("search", "Search", androidx.compose.material.icons.Icons.Filled.Search)
    object Favorites : Screen("favorites", "Favorites", androidx.compose.material.icons.Icons.Filled.Favorite)
    object Profile : Screen("profile", "Profile", androidx.compose.material.icons.Icons.Filled.Person)
}

/**
 * Composable function to display the bottom navigation bar.
 */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Favorites,
        Screen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination and avoid multiple copies of the same destination
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

/**
 * Function to set up the navigation graph for the app.
 * It defines the destinations that can be used in a NavHost.
 *
 * @param navController NavController to navigate between screens.
 * @param builder NavGraphBuilder to which the destinations will be added.
 */
fun NavGraphBuilder.AppNavigation(navController: NavController) {
    composable("home") {
        HomeScreen(navController)
    }
    composable("search") {
        SearchScreen()
    }
    composable("favorites") {
        FavoritesScreen()
    }
    composable("profile") {
        ProfileScreen()
    }
    composable("ShowDetails/{ShowsId}") { backStackEntry ->
        val showsIdString = backStackEntry.arguments?.getString("ShowsId")

        // Convert showsIdString to an Int
        val showsId = showsIdString?.toIntOrNull() // This will safely convert to Int or return null if not possible

        if (showsId != null) {
            ShowsDetailsScreen(showsId) // Pass showsId as an Int to the detail screen
        } else {
            // Log the error and handle the case where showsId is null
            Timber.tag("AppNavigation")
                .e("ShowsId is null or invalid, cannot navigate to ShowsDetailsScreen")
            // Display a user-friendly message or handle error state here
        }
    }
}
