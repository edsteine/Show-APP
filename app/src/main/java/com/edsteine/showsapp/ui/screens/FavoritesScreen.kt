// File: FavoritesScreen.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents the Favorites screen in the ShowsApp,
//              displaying a static list of favorite Shows with images and descriptions.
// Used Libraries: Android SDK, Jetpack Compose, Coil for image loading

package com.edsteine.showsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

/**
 * Data class to represent a Shows item.
 */
data class ShowsItem(
    val title: String,
    val description: String,
    val imageUrl: String
)

/**
 * Composable function to display the Favorites screen content.
 * This function shows a static list of favorite shows with images and descriptions.
 */
@Composable
fun FavoritesScreen() {
    // Static list of favorite shows with real image URLs
    val favoriteShows = listOf(
        ShowsItem(
            title = "Breaking Bad",
            description = "A high school chemistry teacher turned methamphetamine producer.",
            imageUrl = "https://www.amc.com/twilight-zone/images/breaking-bad-s4.jpg"
        ),
        ShowsItem(
            title = "Stranger Things",
            description = "A group of kids in a small town uncovering supernatural mysteries.",
            imageUrl = "https://www.indiewire.com/wp-content/uploads/2022/06/Stranger-Things-4_02.jpg"
        ),
        ShowsItem(
            title = "The Mandalorian",
            description = "The journey of a lone bounty hunter in the Star Wars universe.",
            imageUrl = "https://www.starwars.com/assets/images/mandalorian/season2/mandalorian_s2.png"
        ),
        ShowsItem(
            title = "The Crown",
            description = "A dramatized history of the reign of Queen Elizabeth II.",
            imageUrl = "https://static01.nyt.com/images/2022/11/07/arts/06crown/merlin_207996773_c362fa9b-3e38-46a5-bb3d-125d40161188-superJumbo.jpg"
        ),
        ShowsItem(
            title = "Game of Thrones",
            description = "Noble families vie for control of the Seven Kingdoms of Westeros.",
            imageUrl = "https://static1.srcdn.com/wordpress/wp-content/uploads/2021/03/game-of-thrones.jpg"
        )
    )

    // Display the list of favorite shows
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Favorites", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        favoriteShows.forEach { shows ->
            ShowsCard(shows)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * Composable function to display an individual shows card.
 */
@Composable
fun ShowsCard(shows: ShowsItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(shows.imageUrl),
                contentDescription = shows.title,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = shows.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = shows.description, fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}
