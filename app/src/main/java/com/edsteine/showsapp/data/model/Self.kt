// File: Self.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents a self-reference link with a URL.
// Provides methods to validate the URL and to return its string representation.
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.data.model

import android.os.Parcelable
import android.util.Patterns
import kotlinx.parcelize.Parcelize

/**
 * Represents a self-reference link.
 *
 * @property href The URL of the resource.
 */
@Parcelize
data class Self(
    val href: String?  // URL (nullable)
) : Parcelable {

    /**
     * Checks if the URL is valid.
     *
     * @return True if the URL is valid; otherwise, false.
     */
    fun isValidUrl(): Boolean {
        return href != null && Patterns.WEB_URL.matcher(href).matches()
    }

    /**
     * Returns the URL as a string (or "N/A" if the URL is null).
     *
     * @return The URL string or "N/A".
     */
    override fun toString(): String {
        return href ?: "N/A"  // Handle null case
    }
}
