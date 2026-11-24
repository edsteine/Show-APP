/**
 * File: GenreDeserializer.kt
 * Date updated: 2024-10-16
 * Author: Your Name
 * Email: your.email@example.com
 * Phone: +1234567890
 * Project: ShowsApp
 * Description: Deserializer for the Genre model, handling JSON parsing.
 * Used Libraries: Google Gson
 */

package com.edsteine.showsapp.data.model

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Deserializer for the Genre model.
 */
class GenreDeserializer : JsonDeserializer<List<Genre>> {
    /**
     * Deserializes a JSON element into a list of [Genre] objects.
     *
     * @param json The JSON element to deserialize.
     * @param typeOfT The type of the object to deserialize into.
     * @param context The context for deserialization.
     * @return A list of deserialized [Genre] objects.
     */
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): List<Genre> {
        val jsonArray = json.asJsonArray

        return jsonArray.map { jsonElement ->
            val name = jsonElement.takeIf { !it.isJsonNull }?.asString
            Genre(name) // Create a Genre object with the deserialized name
        }
    }
}
