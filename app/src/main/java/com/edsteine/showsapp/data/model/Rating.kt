// File: Rating.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents the rating of a show and provides various methods to handle ratings
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.data.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import com.edsteine.showsapp.R
import kotlinx.parcelize.Parcelize

/**
 * Represents the rating of a show.
 *
 * This data class implements `Parcelable`, allowing instances of this class
 * to be passed between Android components.
 *
 * @property average The average rating score of the show, typically on a scale of 0 to 10 (nullable).
 */
@Parcelize
data class Rating(
    val average: Double? // The average rating score of the show (nullable)
) : Parcelable {

    /**
     * Returns the rating formatted as a percentage.
     *
     * @return The average rating as a percentage value (0-100).
     */
    fun getRatingAsPercentage(): Double {
        return (average ?: 0.0) * 10.0 // Use 0.0 if average is null, ensuring percentage is within 0-100
    }

    /**
     * Provides a user-friendly string representation of the rating.
     *
     * @return A string indicating the rating, rounded to one decimal place.
     */
    fun getFormattedRating(): String {
        return String.format("%.1f", average ?: 0.0) // Format average to one decimal place, using 0.0 if null
    }

    /**
     * Returns a boolean indicating if the rating is considered good.
     *
     * @return True if the average rating is 7.0 or higher, otherwise false.
     */
    fun isGoodRating(): Boolean {
        return (average ?: 0.0) >= 7.0 // Define a "good" rating as 7.0 or above, using 0.0 if null
    }

    /**
     * Provides a color code representation for the rating based on its value.
     * Useful for displaying the rating with a color in the UI.
     *
     * @return A color resource ID corresponding to the rating level.
     */
    @ColorRes
    fun getRatingColor(): Int {
        return when {
            (average ?: 0.0) >= 8.0 -> R.color.green // Good rating
            (average ?: 0.0) >= 5.0 -> R.color.yellow // Average rating
            else -> R.color.red // Poor rating
        }
    }
}
