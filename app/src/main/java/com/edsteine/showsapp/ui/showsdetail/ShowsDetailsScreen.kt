// File: ShowItem.kt
// Date updated: 2024-10-15
// Author: Adil Ajdaa
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents an individual Show item in the response,
// including its name, genres, runtime, and associated network information.
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.ui.showsdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.edsteine.showsapp.data.model.ShowDetails
import com.edsteine.showsapp.viewmodel.ShowsViewModel

/**
 * Composable function for displaying the details of a TV show.
 * It fetches the show's details based on the provided ID.
 *
 * @param showsId The ID of the show to fetch details for.
 */
@Composable
fun ShowsDetailsScreen(showsId: Int?) {
    val viewModel: ShowsViewModel = hiltViewModel()

    // Fetch show details when showsId changes
    LaunchedEffect(showsId) {
        showsId?.let { viewModel.getShowsDetails(it) }
    }

    val showsDetailsUiState by viewModel.showsDetailsUiState.collectAsState()

    // Show loading state
    when (showsDetailsUiState) {
        is ShowsViewModel.ShowsDetailsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ShowsViewModel.ShowsDetailsUiState.Success -> {
            val showsDetails = (showsDetailsUiState as ShowsViewModel.ShowsDetailsUiState.Success).showDetails
            showsDetails?.let { ShowsDetailsContent(it) }
        }
        is ShowsViewModel.ShowsDetailsUiState.Error -> {
            Text("Error: ${(showsDetailsUiState as ShowsViewModel.ShowsDetailsUiState.Error).message}")
        }
    }
}

/**
 * Composable function to display the content of a show's details.
 *
 * @param showDetails The [ShowDetails] object containing the show's details.
 */
@Composable
fun ShowsDetailsContent(showDetails: ShowDetails) {
    Column(modifier = Modifier.padding(16.dp)) {
        val posterPainter: Painter = rememberAsyncImagePainter(showDetails.image)

        Image(
            painter = posterPainter,
            contentDescription = "Shows poster for ${showDetails.name}",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        showDetails.name?.let {
            Text(
                text = it,
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Release Date: ${showDetails.premiered}", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Rating: ${showDetails.rating}", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        showDetails.summary?.let { Text(text = it, fontSize = 16.sp) }
    }
}
