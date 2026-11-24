// File: RatingDeserializer.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Deserializer for the Rating model, handling JSON parsing
// Used Libraries: Google Gson

package com.edsteine.showsapp.data.model

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Deserializer for the Rating model.
 */
class RatingDeserializer : JsonDeserializer<Rating> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Rating {
        val jsonObject = json.asJsonObject

        val average = if (jsonObject.get("average") != null && !jsonObject.get("average").isJsonNull) {
            jsonObject.get("average")?.asDouble
        } else {
            null
        }
        // Create and return the Rating instance
        return Rating(average)
    }
}
