// File: ScheduleDeserializer.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Deserializer for the Schedule model, handling JSON parsing
// Used Libraries: Google Gson

package com.edsteine.showsapp.data.model

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Deserializer for the Schedule model.
 */
class ScheduleDeserializer : JsonDeserializer<Schedule> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Schedule {
        val jsonObject = json.asJsonObject

        // Extract the time and days from the JSON object

        val time = if (jsonObject.get("time") != null && !jsonObject.get("time").isJsonNull) {
            jsonObject.get("time")?.asString
        } else {
            null
        }
        val days = if (jsonObject.get("days") != null && !jsonObject.get("days").isJsonNull) {
            jsonObject.getAsJsonArray("days").map { it.asString }
        } else {
            null
        }
        // Create and return the Schedule instance
        return Schedule(time, days)
    }
}
