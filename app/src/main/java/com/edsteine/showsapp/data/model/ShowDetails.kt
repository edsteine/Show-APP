// File: ShowDetails.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents an individual Show Detail in the response,
// including its name, genres, runtime, and associated network information.
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents an individual Show Detail in the response.
 *
 * This data class implements `Parcelable`, allowing instances of this class
 * to be passed between Android components.
 *
 * @property genres List of genres associated with the Show (nullable).
 * @property id Unique identifier for the Show.
 * @property image Image URLs for different resolutions (nullable).
 * @property language Language in which the Show is available (nullable).
 * @property name Name of the Show (nullable).
 * @property officialSite Official website URL of the Show (nullable).
 * @property premiered Premiere date of the Show (nullable).
 * @property ended End date of the Show (nullable).
 * @property rating Rating information (nullable).
 * @property runtime Runtime of the Show in minutes (nullable).
 * @property averageRuntime Average runtime of the Show in minutes (nullable).
 * @property status Current status of the Show (e.g., Running, Ended) (nullable).
 * @property summary Brief description of the Show (nullable).
 * @property type Type of the Show (e.g., Scripted, Reality) (nullable).
 * @property weight Priority weight of the Show (nullable).
 * @property network Information about the Show's network (nullable).
 * @property webChannel Information about the web channel (nullable).
 * @property dvdCountry Country of the DVD release (nullable).
 * @property externals External identifiers for the Show (nullable).
 * @property schedule Scheduling information (nullable).
 * @property updated Last updated timestamp (nullable).
 * @property links Related links for the Show (nullable).
 */
@Parcelize
data class ShowDetails(
    val genres: List<String>? = null,
    val id: Int,
    val image: Image? = null,
    val language: String? = null,
    val name: String? = null,
    val officialSite: String? = null,
    val premiered: String? = null,
    val ended: String? = null,
    val rating: Rating? = null,
    val runtime: Int? = null,
    val averageRuntime: Int? = null, // Added this property
    val status: String? = null,
    val summary: String? = null,
    val type: String? = null,
    val weight: Int? = null,
    val network: Network? = null,
    val webChannel: WebChannel? = null,
    val dvdCountry: Country? = null,
    val externals: Externals? = null,
    val schedule: Schedule? = null,
    val updated: Long? = null,
    val links: Links? = null // Updated to include Links
) : Parcelable {

    fun getShowsDetails(): String {
        return "${name ?: "Unknown Show"} (Premiered: ${premiered ?: "Unknown Date"})"
    }

    fun isCurrentlyAiring(): Boolean {
        return status.equals("Running", ignoreCase = true)
    }

    fun getGenreNames(): List<String> {
        return genres?.map { genre ->
            genre.replaceFirstChar { it.titlecase() }
        } ?: emptyList()
    }


}
