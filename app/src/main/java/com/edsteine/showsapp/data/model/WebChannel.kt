package com.edsteine.showsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents a web channel associated with a show.
 * The `WebChannel` class contains information about the web channel,
 * including its ID, name, country, and official website.
 *
 * This data class implements `Parcelable`, allowing instances of this class
 * to be passed between Android components.
 *
 * @property id Unique identifier for the web channel.
 * @property name Name of the web channel.
 * @property country The country where the web channel is based (nullable).
 * @property officialSite The official website of the web channel.
 */


/**
 * Represents a web channel associated with a show.
 */
@Parcelize
data class WebChannel(
    val id: Int?, // ID of the web channel
    val name: String?, // Name of the web channel
    val country: Country? = null, // Country where the web channel is based (nullable)
    val officialSite: String? = null // Official website of the web channel (nullable)
) : Parcelable {



    /**
     * Returns a string representation of the web channel.
     */
    override fun toString(): String {
        return "WebChannel(id=$id, name='$name', country=$country, officialSite=$officialSite)"
    }
}
