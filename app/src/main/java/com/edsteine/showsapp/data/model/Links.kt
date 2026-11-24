// File: Links.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents the links associated with a Show,
// including references to the previous episode and the current resource.
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents the links associated with a Show.
 * The `Links` class contains references to related resources, such as
 * previous episodes and the main resource itself.
 *
 * @property self A link to the current resource.
 */
@Parcelize
data class Links(
    val self: Self                        // Represents a reference to the current resource
) : Parcelable {



    /**
     * Retrieves the URL for the current resource.
     *
     * @return The URL of the current resource.
     */
    fun getSelfUrl(): String? {
        return self.href
    }
}
