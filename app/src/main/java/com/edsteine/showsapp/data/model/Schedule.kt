// File: Schedule.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents the schedule for a show,
// including the broadcast time and the days of the week it airs.
// Used Libraries: None

package com.edsteine.showsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents the schedule for a show.
 *
 * @property time The time the show airs (e.g., "20:00").
 * @property days The list of days of the week when the show airs (e.g., ["Monday", "Wednesday"]).
 */


@Parcelize
data class Schedule(
    val time: String?,            // The time the show airs
    val days: List<String>?      // The list of days the show airs
) : Parcelable {
    /**
     * Returns a formatted string representation of the schedule.
     *
     * @return A string indicating the time and days of the week the show airs.
     */
    fun getFormattedSchedule(): String {
        return "Airs at $time on ${days?.joinToString(", ")}"
    }
}
