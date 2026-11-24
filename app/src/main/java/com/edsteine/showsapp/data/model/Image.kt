// File: Image.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents image data with URLs for different sizes
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.data.model

import android.os.Parcelable
import android.util.Patterns // Import Patterns for URL validation
import kotlinx.parcelize.Parcelize

/**
 * Represents image data with URLs for different sizes.
 *
 * @property medium URL for the medium-sized image (nullable).
 * @property original URL for the original image (nullable).
 */
@Parcelize
data class Image(
    val medium: String?,    // URL for a medium-sized image (nullable)
    val original: String?   // URL for the original image (nullable)
) : Parcelable {

    /**
     * Returns the image URL for the specified size.
     *
     * @param size The desired size of the image ("medium" or "original").
     * @return The URL for the specified image size, or null if invalid or not available.
     */
    fun getImageUrl(size: String): String? {
        return when (size) {
            "medium" -> medium
            "original" -> original
            else -> null // Return null for any invalid size request
        }
    }

    /**
     * Checks if both image URLs are valid using Android's `Patterns.WEB_URL`.
     *
     * @return True if both URLs are valid, false otherwise.
     */
    fun areImageUrlsValid(): Boolean {
        return isValidUrl(medium) && isValidUrl(original)
    }

    /**
     * Helper function that checks if a given URL is valid.
     *
     * @param url The URL to validate.
     * @return True if the URL is valid, false otherwise.
     */
    private fun isValidUrl(url: String?): Boolean {
        return url?.let { Patterns.WEB_URL.matcher(it).matches() } ?: false // Validate the URL format
    }
}
