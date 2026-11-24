// File: Genre.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents a genre in the application
// Used Libraries: Kotlin standard library

package com.edsteine.showsapp.data.model

import java.util.Locale

/**
 * Represents a genre in the application.
 *
 * @property name The name of the genre.
 */
data class Genre(
    val name: String? // The name of the genre (nullable)
) {

    /**
     * Returns the genre name in a user-friendly format.
     *
     * @return The genre name capitalized, or "Unknown Genre" if name is null.
     */
    fun getFormattedName(): String {
        return name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        } ?: "Unknown Genre" // Capitalizes the first letter or returns "Unknown Genre" if null
    }

    /**
     * Checks if the genre name is valid.
     *
     * @return True if the genre name is not null or empty, otherwise false.
     */
    fun isValid(): Boolean {
        return !name.isNullOrBlank() // Ensure the genre name is not null or blank
    }

    /**
     * Provides a string representation of the Genre object.
     *
     * @return A string representing the genre name, or "Unknown Genre" if name is null.
     */
    override fun toString(): String {
        return name ?: "Unknown Genre" // Returns the genre name or "Unknown Genre" if null
    }
}
