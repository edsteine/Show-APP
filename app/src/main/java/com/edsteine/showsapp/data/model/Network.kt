// File: Network.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents the network that broadcasts a show,
// including its country of origin, unique identifier, and name.
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents the network that broadcasts a show.
 * The `Network` class contains information about the network,
 * including its country of origin, unique identifier, and name.
 *
 * This data class implements `Parcelable`, allowing instances of this class
 * to be passed between Android components.
 *
 * @property country The country where the network is based.
 * @property id Unique identifier for the network.
 * @property name Name of the network.
 */
@Parcelize
data class Network(
    val country: Country?, // The country where the network is based
    val id: Int?,         // Unique identifier for the network
    val name: String?     // Name of the network
) : Parcelable {

    /**
     * Returns a formatted string with the network's name and country.
     *
     * @return A string representation of the network's name and its country.
     */
    fun getNetworkDetails(): String {
        return "$name (${country?.name})" // Combine network name with country name
    }

    /**
     * Checks if the network is from a specific country.
     *
     * @param targetCountry The country to check against.
     * @return True if the network's country matches the target country; otherwise, false.
     */
    fun isNetworkFrom(targetCountry: String): Boolean {
        return country?.code.equals(targetCountry, ignoreCase = true) // Check country code
    }
}
