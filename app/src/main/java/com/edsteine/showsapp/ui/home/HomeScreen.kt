// File: ShowsScreen.kt
// Date updated: 2024-10-15
// Author: Adil Ajdaa
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Composable functions to display a list of shows in a scrollable format.
//              The list includes both horizontal and vertical displays of individual show items that are clickable.
// Used Libraries: Android SDK, Jetpack Compose, Hilt for Dependency Injection, Timber for logging

package com.edsteine.showsapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.edsteine.showsapp.data.model.ShowItem
import com.edsteine.showsapp.data.remote.ShowClickListener
import com.edsteine.showsapp.viewmodel.ShowsViewModel
import timber.log.Timber

/**
 * HomeScreen composable function to display a list of shows.
 *
 * This function shows both horizontal and vertical lists of shows.
 * It fetches show data using the ShowsViewModel.
 *
 * @param navController Navigation controller to handle navigation actions.
 */
@OptIn(ExperimentalMaterial3Api::class) // Opt in to Material3 API
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: ShowsViewModel = hiltViewModel()

    // State to hold UI state
    val showsUiState by viewModel.showsUiState.collectAsState()
    var showsList by remember { mutableStateOf(emptyList<ShowItem>()) }

    // Collect Shows data from ViewModel
    LaunchedEffect(showsUiState) {
        when (showsUiState) {
            is ShowsViewModel.ShowsUiState.Success -> {
                val successState = showsUiState as? ShowsViewModel.ShowsUiState.Success
                successState?.let {
                    showsList = it.shows.toList() // Handle nullability here
                    Timber.tag("HomeScreen").d("Fetched shows successfully: ${showsList.size} shows")
                }
            }
            is ShowsViewModel.ShowsUiState.Error -> {
                val errorState = showsUiState as? ShowsViewModel.ShowsUiState.Error
                errorState?.let {
                    Timber.tag("HomeScreen").e("Error fetching shows: ${it.message}")
                    // Handle error appropriately
                }
            }
            ShowsViewModel.ShowsUiState.Loading -> {
                Timber.tag("HomeScreen").d("Loading shows...")
            }
        }
    }

    // Top-level LazyColumn for scrollable content
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        // Refresh Button
        // Refresh Button
        item {
            Button(
                onClick = {
                    viewModel.fetchShows() // Call fetchShows when button is clicked
                    // You can also log the refresh action if necessary
                    Timber.tag("HomeScreen").d("Refresh button clicked")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Refresh Shows")
            }
        }


        // Horizontal Shows List as a single item
        item {
            HorizontalShowsList(
                showsList = showsList.take(5),
                clickListener = object : ShowClickListener {
                    override fun onShowClicked(showItem: ShowItem) {
                        navController.navigate("ShowDetails/${showItem.id}")
                        Timber.tag("HomeScreen").d("Navigating to details for show: ${showItem.id}")
                    }
                }
            )
        }

        // Spacer
        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Vertical Shows List
        items(showsList.drop(5)) { show ->
            ShowListItem(show = show, clickListener = object : ShowClickListener {
                override fun onShowClicked(showItem: ShowItem) {
                    navController.navigate("ShowDetails/${showItem.id}")
                    Timber.tag("HomeScreen").d("Navigating to details for show: ${showItem.id}")
                }
            })
        }
    }
}

/**
 * Composable function to display a list of shows in a horizontal scrolling format.
 *
 * @param showsList The list of show items to display.
 * @param clickListener The listener for show click events.
 */
@Composable
fun HorizontalShowsList(showsList: List<ShowItem>, clickListener: ShowClickListener) {
    LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {
        items(showsList) { show ->
            HorizontalShowsItem(showResponse = show, clickListener = clickListener)
        }
    }
}

/**
 * Composable function to display a single show item in a vertical list.
 *
 * This function displays the show name and registers click events
 * to notify the listener.
 *
 * @param show The show item to display.
 * @param clickListener The listener for show click events.
 */
@Composable
fun ShowListItem(show: ShowItem, clickListener: ShowClickListener) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                clickListener.onShowClicked(show)
                Timber.tag("ShowListItem").d("Show clicked: ${show.name}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        show.name?.let {
            Text(
                text = it,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
