package com.edsteine.showsapp.ui.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.edsteine.showsapp.R
import com.edsteine.showsapp.ui.theme.ShowsTheme
import kotlinx.coroutines.delay

/**
 * SplashActivity: This activity is responsible for displaying the splash screen
 * before navigating to the main application.
 *
 * Updated on: 2024-10-15
 */
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen() // Composable function for the splash screen
                }
            }
        }
    }
}

/**
 * SplashScreen: Displays the splash screen image and handles navigation to MainActivity.
 *
 * The splash screen is displayed for 2 seconds before navigating to the main app screen.
 */
@Composable
fun SplashScreen() {
    // Access the current context safely within a Composable
    val context = LocalContext.current

    // Launching a coroutine effect for navigation
    LaunchedEffect(Unit) {
        delay(2000) // Show splash for 2 seconds
        navigateToMainActivity(context) // Start MainActivity after the splash
    }

    // Box to center the splash image
    Box(modifier = Modifier.fillMaxSize()) {
        // Load your splash image from drawable resources
        Image(
            painter = painterResource(id = R.drawable.splash_image), // Replace with your image
            contentDescription = null, // Set content description for accessibility
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * Navigate to MainActivity
 *
 * This function encapsulates the intent creation and activity launching.
 */
private fun navigateToMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
    context.startActivity(intent)
}

// Preview function for the SplashScreen composable
@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    ShowsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SplashScreen() // Previewing the splash screen
        }
    }
}
