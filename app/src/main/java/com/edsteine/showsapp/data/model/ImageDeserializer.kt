/**
 * File: ImageDeserializer.kt
 * Date updated: 2024-10-16
 * Author: Your Name
 * Email: your.email@example.com
 * Phone: +1234567890
 * Project: ShowsApp
 * Description: Deserializer for the Image model, handling JSON parsing.
 * Used Libraries: Google Gson
 */

package com.edsteine.showsapp.data.model

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Deserializer for the Image model.
 */
class ImageDeserializer : JsonDeserializer<Image> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Image {
        val jsonObject = json.asJsonObject

        // Extract the URLs from the JSON object
        val original = jsonObject.get("original")?.takeIf { !it.isJsonNull }?.asString
        val medium = jsonObject.get("medium")?.takeIf { !it.isJsonNull }?.asString

        // Create and return the Image instance
        return Image(medium, original)
    }
}
