package com.edsteine.showsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents an individual Show item in the response.
 *
 * The `ShowItem` class includes various properties related to a Show,
 * such as its name, genres, runtime, and associated network information.
 *
 * This data class implements `Parcelable`, allowing instances of this class
 * to be passed between Android components.
 */
/**
 * Represents an individual Show item in the response.
 *
 * The `ShowItem` class includes various properties related to a Show,
 * such as its name, genres, runtime, and associated network information.
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
data class ShowItem(
    val genres: List<String>?, // List of genres (nullable)
    val id: Int, // Unique Show identifier
    val image: Image?, // Image URLs for the Show (nullable)
    val language: String?, // Show language (nullable)
    val name: String?, // Show name (nullable)
    val officialSite: String?, // Official website (nullable)
    val premiered: String?, // Premiere date (nullable)
    val ended: String?, // End date (nullable)
    val rating: Rating?, // Rating information (nullable)
    val runtime: Int?, // Runtime in minutes (nullable)
    val status: String?, // Current status (Running, Ended) (nullable)
    val summary: String?, // Show summary (nullable)
    val type: String?, // Show type (Scripted, Reality) (nullable)
    val weight: Int?, // Show priority weight (nullable)
    val network: Network?, // Network information (nullable)
    val webChannel: WebChannel?, // Web channel (nullable)
    val dvdCountry: Country?, // DVD country (nullable)
    val externals: Externals?, // External identifiers (nullable)
    val schedule: Schedule?, // Scheduling information (nullable)
    val updated: Long?, // Last updated timestamp (nullable)
    val links: Links? // Related links (nullable)
) : Parcelable {

    /**
     * Returns a formatted string with the Show's title and its premiere date.
     */
    fun getShowDetails(): String {
        return "${name ?: "Unknown Show"} (Premiered: ${premiered ?: "Unknown Date"})"
    }

    /**
     * Checks if the Show is currently airing based on its status.
     */
    fun isCurrentlyAiring(): Boolean {
        return status?.equals("Running", ignoreCase = true) == true
    }

    /**
     * Returns a list of genre names associated with the Show, formatted to title case.
     */
    fun getGenreNames(): List<String> {
        return genres?.map { genre ->
            genre.replaceFirstChar { char ->
                if (char.isLowerCase()) char.titlecase() else char.toString()
            }
        } ?: emptyList()
    }
}


