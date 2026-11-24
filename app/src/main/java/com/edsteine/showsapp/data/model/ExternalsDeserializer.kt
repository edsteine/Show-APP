/**
 * File: ExternalsDeserializer.kt
 * Date updated: 2024-10-16
 * Author: Your Name
 * Email: your.email@example.com
 * Phone: +1234567890
 * Project: ShowsApp
 * Description: Deserializer for the Externals model, handling JSON parsing.
 * Used Libraries: Google Gson
 */

package com.edsteine.showsapp.data.model

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Deserializer for the Externals model.
 *
 * This class implements [JsonDeserializer] to provide custom
 * deserialization logic for the [Externals] data class.
 */
class ExternalsDeserializer : JsonDeserializer<Externals> {

    /**
     * Deserializes a JSON element into an [Externals] object.
     *
     * @param json The JSON element to deserialize.
     * @param typeOfT The type of the object to deserialize into.
     * @param context The context for deserialization.
     * @return The deserialized [Externals] object.
     */
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Externals {
        val jsonObject = json.asJsonObject

        val imdb = jsonObject.get("imdb")?.takeIf { !it.isJsonNull }?.asString
        val tvrage = jsonObject.get("tvrage")?.takeIf { !it.isJsonNull }?.asInt
        val thetvdb = jsonObject.get("thetvdb")?.takeIf { !it.isJsonNull }?.asInt

        return Externals(
            imdb = imdb,
            thetvdb = thetvdb,
            tvrage = tvrage
        )
    }
}
