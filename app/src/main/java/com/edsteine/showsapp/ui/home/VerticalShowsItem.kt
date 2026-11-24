// File: VerticalShowsItem.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Composable function that displays a show item in a card format,
//              including the show's poster and handling click events for navigation.
// Used Libraries: Android SDK, Jetpack Compose, Coil for image loading, Timber for logging

package com.edsteine.showsapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.edsteine.showsapp.data.model.ShowItem
import com.edsteine.showsapp.data.remote.ShowClickListener
import timber.log.Timber // Import Timber for logging

/**
 * Composable function that displays a show item in a card format.
 *
 * @param showResponse The ShowItem object containing show details.
 * @param clickListener The listener for show click events.
 */
@Composable
fun VerticalShowsItem(showResponse: ShowItem, clickListener: ShowClickListener) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                // Log the click event for debugging
                Timber.d("Show clicked: ${showResponse.name}")
                clickListener.onShowClicked(showResponse)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp) // Use Material3 elevation
    ) {
        // Load show's poster using Coil
        AsyncImage(
            model = showResponse.image?.original,
            contentDescription = "Poster for ${showResponse.name}", // Add a content description for accessibility
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
    }
}
