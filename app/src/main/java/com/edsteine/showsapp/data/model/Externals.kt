// File: CountryDeserializer.kt
// Date updated: 2024-10-16
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Deserializer for the Country model, handling JSON parsing.
// Used Libraries: Google Gson

package com.edsteine.showsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents external identifiers for a show.
 *
 * @property imdb The IMDb ID (nullable).
 * @property thetvdb The TVDB ID (nullable).
 * @property tvrage The TVRage ID (nullable).
 */
@Parcelize
data class Externals(
    val imdb: String?,    // IMDb ID (nullable)
    val thetvdb: Int?,   // TVDB ID (nullable)
    val tvrage: Int?      // TVRage ID (nullable)
) : Parcelable {

    /**
     * Returns a formatted string representation of the external identifiers.
     */
    fun getFormattedExternalIds(): String {
        return "IMDb: $imdb, TVDB: $thetvdb, TVRage: $tvrage"
    }

    /**
     * Checks if the IMDb ID might be valid. Not foolproof; use with caution.
     * Prefer IMDb's official validation if possible.
     */
    fun isValidImdbId(): Boolean {
        return imdb?.matches(Regex("^tt\\d{7,9}\$")) ?: false // More flexible regex, handles nulls
    }

    companion object {
        /**
         * A more robust way to validate an IMDb ID.
         * Checks the general format and ensures the ID starts with "tt" followed by digits.
         * Still, IMDb's own API is the most reliable method for validation.
         */
        fun isProbablyValidImdbId(imdbId: String?): Boolean {
            if (imdbId == null) return false
            if (!imdbId.startsWith("tt")) return false
            val numericPart = imdbId.substring(2)
            return numericPart.all { it.isDigit() } // Basic format check passes
        }
    }
}
