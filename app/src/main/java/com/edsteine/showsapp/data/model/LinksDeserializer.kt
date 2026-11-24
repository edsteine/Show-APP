/**
 * File: LinksDeserializer.kt
 * Date updated: 2024-10-16
 * Author: Your Name
 * Email: your.email@example.com
 * Phone: +1234567890
 * Project: ShowsApp
 * Description: Deserializer for the Links model, handling JSON parsing
 * Used Libraries: Google Gson
 */

package com.edsteine.showsapp.data.model

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Deserializer for the Links model.
 */
class LinksDeserializer : JsonDeserializer<Links> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Links {
        val jsonObject = json.asJsonObject

        // Extract the "self" object and its "href" property
        val selfJson = jsonObject.getAsJsonObject("self")
        val href = if (selfJson.get("href") != null && !selfJson.get("href").isJsonNull) {
            selfJson.get("href")?.asString // Corrected to fetch href from selfJson
        } else {
            null
        }
        // Create a Self instance
        val self = Self(href)

        // Create and return the Links instance
        return Links(self)
    }
}
