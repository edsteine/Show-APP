// File: SearchScreen.kt
// Date updated: 2024-10-15
// Author: Adil Ajdaa
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Displays the Search screen content in a grid layout,
//              resembling Instagram's interface, showing a list of Shows.
// Used Libraries: Android SDK, Jetpack Compose, Coil for image loading

package com.edsteine.showsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

// Sample data class to represent a Shows item
data class ShowsItemSearch(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)

// Sample data for demonstration
val showsList = listOf(
    ShowsItemSearch(1, "Breaking Bad", "A high school chemistry teacher turned methamphetamine producer.", "https://example.com/breakingbad.jpg"),
    ShowsItemSearch(2, "Stranger Things", "A group of kids uncover a supernatural mystery.", "https://example.com/strangerthings.jpg"),
    ShowsItemSearch(3, "Game of Thrones", "Noble families vie for control of the Iron Throne.", "https://example.com/gameofthrones.jpg"),
    ShowsItemSearch(4, "The Office", "A mockumentary on a group of office workers.", "https://example.com/theoffice.jpg"),
    // Add more showsList as needed
)

/**
 * Composable function to display the Search screen content in a grid layout.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val filteredShowsList = showsList.filter {
        it.title.contains(searchQuery, ignoreCase = true) ||
                it.description.contains(searchQuery, ignoreCase = true)
    }

    Column {
        // Header with search
        TopAppBar(
            title = {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search...") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.primary, // Change this according to your design
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface, // Default color when not focused
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
        )

        // Grid of shows items only if search query is empty
        if (searchQuery.isEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Two columns like Instagram
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(showsList) { shows ->
                    ShowsCardSearch(shows)
                }
            }
        } else {
            // Show filtered results based on the search query
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(filteredShowsList) { shows ->
                    ShowsCardSearch(shows)
                }
            }
        }
    }
}

/**
 * Composable function to display an individual shows card.
 */
@Composable
fun ShowsCardSearch(shows: ShowsItemSearch) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(shows.imageUrl),
                contentDescription = shows.title,
                modifier = Modifier
                    .height(200.dp) // Fixed height for images
                    .fillMaxWidth()
            )
            Text(
                text = shows.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = shows.description,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}
