// File: HorizontalShowsItem.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Composable function to display a single horizontal show item,
//              including the show's poster and click handling for navigation.
//              Uses Coil for image loading.
// Used Libraries: Android SDK, Jetpack Compose, Coil for image loading

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

/**
 * Composable function to display a single horizontal show item.
 *
 * @param showResponse The show item to display.
 * @param clickListener Listener for handling click events.
 */
@Composable
fun HorizontalShowsItem(showResponse: ShowItem, clickListener: ShowClickListener) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .padding(10.dp)
            .clickable { clickListener.onShowClicked(showResponse) },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        // Load show's poster using Coil
        AsyncImage(
            model = showResponse.image?.original,
            contentDescription = "Poster of ${showResponse.name}", // Content description for accessibility
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )
    }
}
