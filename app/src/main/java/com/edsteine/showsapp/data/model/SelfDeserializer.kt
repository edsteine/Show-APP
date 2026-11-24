//File: SelfDeserializer.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Deserializer for the Self model, handling JSON parsing
// Used Libraries: Google Gson

package com.edsteine.showsapp.data.model

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Deserializer for the Self model.
 */
class SelfDeserializer : JsonDeserializer<Self> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Self {
        val jsonObject = json.asJsonObject

        // Extract the href from the JSON object

        val href = if (jsonObject.get("href") != null && !jsonObject.get("href").isJsonNull) {
            jsonObject.get("href")?.asString
        } else {
            null
        }
        // Create and return the Self instance
        return Self(href)
    }
}
