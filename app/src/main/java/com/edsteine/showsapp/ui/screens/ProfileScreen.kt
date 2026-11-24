// File: ProfileScreen.kt
// Date updated: 2024-10-15
// Author: Adil Ajdaa
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Displays the user profile information, including user details and preferences.
// Used Libraries: Android SDK, Jetpack Compose

package com.edsteine.showsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

// Sample User Data Class
data class User(
    val name: String,
    val email: String,
    val phone: String,
    val profilePictureUrl: String
)

// Sample user data for demonstration
val sampleUser = User(
    name = "Adil Ajdaa",
    email = "your.email@example.com",
    phone = "+1234567890",
    profilePictureUrl = "https://example.com/profilepic.jpg" // Replace with a real URL
)

/**
 * Composable function to display the Profile screen content.
 */
@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Profile Picture
        Image(
            painter = rememberAsyncImagePainter(sampleUser.profilePictureUrl),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { /* Add action for editing profile picture */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Info
        Text(text = sampleUser.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = sampleUser.email, fontSize = 16.sp, color = Color.Gray)
        Text(text = sampleUser.phone, fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        // Navigation Buttons
        TextButton(
            onClick = { /* Navigate to Settings */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Settings")
        }

        TextButton(
            onClick = { /* Navigate to Language Selection */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Change Language")
        }

        TextButton(
            onClick = { /* Navigate to Help and Support */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Help & Support")
        }

        TextButton(
            onClick = { /* Log out the user */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Log Out")
        }
    }
}
