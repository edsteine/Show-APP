// File: ShowsTheme.kt
// Date updated: 2024-10-15
// Author: Adil Ajdaa
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Contains the custom theme definitions for the ShowsApp,
// including typography styles and color schemes for both light and dark modes.
// Used Libraries: Android SDK, Jetpack Compose

package com.edsteine.showsapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Custom Typography styles for the app.
 * Updated on: 2024-10-15
 */
val Typography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    // Body text style for readability
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    )
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE), // Main primary color
    secondary = Color(0xFF03DAC5), // Secondary color
    background = Color(0xFFFFFFFF), // Background color for light theme
    surface = Color(0xFFFFFFFF), // Surface color for light theme
    onPrimary = Color.White, // Text color on primary
    onSecondary = Color.Black, // Text color on secondary
    onBackground = Color.Black, // Text color on background
    onSurface = Color.Black // Text color on surface
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC), // Main primary color
    secondary = Color(0xFF03DAC6), // Secondary color
    background = Color(0xFF121212), // Background color for dark theme
    surface = Color(0xFF121212), // Surface color for dark theme
    onPrimary = Color.Black, // Text color on primary
    onSecondary = Color.White, // Text color on secondary
    onBackground = Color.White, // Text color on background
    onSurface = Color.White // Text color on surface
)

/**
 * Composable function to apply the Shows theme based on dark/light mode.
 *
 * @param darkTheme Boolean flag to determine if dark theme is enabled.
 * @param content The content to be wrapped in the theme.
 */
@Composable
fun ShowsTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    // Choose color scheme based on the dark theme flag
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    // Apply the MaterialTheme with the selected color scheme and typography
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
