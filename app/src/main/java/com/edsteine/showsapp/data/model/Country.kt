// File: Country.kt
// Date updated: 2024-10-16
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents a country related to show data,
// including its code, name, and timezone.
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents a country related to show data.
 *
 * @property code Country code (e.g., "US")
 * @property name Name of the country (e.g., "United States")
 * @property timezone Timezone (e.g., "America/New_York")
 */
@Parcelize
data class Country(
    val code: String?, // Country code (e.g., "US")
    val name: String?, // Name of the country (e.g., "United States")
    val timezone: String? // Timezone (e.g., "America/New_York")
) : Parcelable {

    /**
     * Returns a formatted string representation of the country.
     *
     * @return A string in the format "Name (Code) - Timezone: Timezone"
     */
    fun getFormattedCountryInfo(): String {
        return "$name ($code) - Timezone: $timezone"
    }

    /**
     * Checks if the country is a member of the European Union.
     *
     * @return True if the country code is in the EU country codes list, false otherwise.
     */
    fun isEuropeanUnionMember(): Boolean {
        val euCountryCodes = listOf(
            "AT", "BE", "BG", "HR", "CY", "CZ", "DK", "EE", "FI", "FR", "DE",
            "GR", "HU", "IE", "IT", "LT", "LU", "LV", "MT", "NL", "PL", "PT",
            "RO", "SK", "SI", "ES", "SE"
        )
        return code in euCountryCodes
    }
}
