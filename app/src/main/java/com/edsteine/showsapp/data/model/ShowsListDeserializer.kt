// File: ShowsList.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents a response containing a map of Shows keyed by their IDs.
//              This class encapsulates a collection of shows and provides utility functions
//              for retrieving shows by ID and getting a list of show names.
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.data.model
import com.google.gson.*
import java.lang.reflect.Type

class ShowsListDeserializer : JsonDeserializer<ShowsList> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ShowsList {
        val jsonObject = json.asJsonObject
        val showsMap = mutableMapOf<Int, ShowItem>()

        // Assuming the JSON structure is {"shows": [{"id": 1, "name": "Show1", ...}, ...]}
        val showsArray = jsonObject.getAsJsonArray("shows")

        for (element in showsArray) {
            val showItem = context.deserialize<ShowItem>(element, ShowItem::class.java)
            showsMap[showItem.id] = showItem
        }

        return ShowsList(showsMap)
    }
}

