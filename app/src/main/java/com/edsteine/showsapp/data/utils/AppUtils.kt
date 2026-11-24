// File: AppUtils.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Utility class that contains configuration values, extension functions,
//              DiffUtil implementation, and resource states used throughout the application.
// Used Libraries: Android SDK, Kotlinx

package com.edsteine.showsapp.data.utils

import androidx.recyclerview.widget.DiffUtil
import com.edsteine.showsapp.data.model.ShowItem
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import timber.log.Timber

/**
 * Utility class that contains configuration values, extension functions,
 * DiffUtil implementation, and resource states used throughout the application.
 */
object AppUtils {

    // Base URL for the Shows API
    const val BASE_URL = "https://api.tvmaze.com/"

    // Starting page number for pagination
    const val START_PAGE = 0

    /**
     * Extension function to format an integer representing minutes
     * into a string displaying hours and minutes.
     *
     * @return A string in the format "H:MM:00", where H is hours and MM is minutes.
     */
    fun Int.formatHourMinutes(): String {
        val hours = this / 60 // Calculate the number of hours
        val minutes = this % 60 // Calculate the remaining minutes
        return String.format("%d:%02d:00", hours, minutes) // Format the string
    }

    /**
     * Utility object for calculating the difference between two lists of ShowItem.
     */
    val diffUtil = object : DiffUtil.ItemCallback<ShowItem>() {
        /**
         * Check if two items represent the same item.
         *
         * @param oldItem The old item in the list.
         * @param newItem The new item in the list.
         * @return True if the items represent the same item, false otherwise.
         */
        override fun areItemsTheSame(oldItem: ShowItem, newItem: ShowItem): Boolean {
            return oldItem.id == newItem.id // Use id for unique identification
        }

        /**
         * Check if the contents of two items are the same.
         *
         * @param oldItem The old item in the list.
         * @param newItem The new item in the list.
         * @return True if the contents are the same, false otherwise.
         */
        override fun areContentsTheSame(oldItem: ShowItem, newItem: ShowItem): Boolean {
            return oldItem == newItem // Compare entire objects for content equality
        }
    }

    /**
     * Retrieves a nullable value of the specified type from the given JsonObject for the specified key.
     *
     * @param jsonObject The JsonObject to extract the value from.
     * @param key The key to look for in the JsonObject.
     * @return The value of type T if present and not null, otherwise null.
     */

    inline fun <reified T> getValueFromJson(input: Any, key: String): T? {
        return try {
            // Handle the case where the input is a JsonObject
            val jsonObject = when (input) {
                is JsonObject -> input
                is String -> JsonParser.parseString(input).asJsonObject // Parse String to JsonObject
                else -> throw IllegalArgumentException("Input must be a JsonObject or a JSON string")
            }

            if (jsonObject.has(key)) {
                val jsonElement = jsonObject.get(key)
                when {
                    jsonElement.isJsonNull -> null
                    jsonElement.isJsonArray && T::class == List::class -> {
                        // Convert JsonArray to List<T>
                        jsonElement.asJsonArray.mapNotNull { element ->
                            when {
                                element.isJsonPrimitive -> when (T::class) {
                                    String::class -> element.asString as T
                                    Int::class -> element.asInt as T
                                    Double::class -> element.asDouble as T
                                    Long::class -> element.asLong as T
                                    else -> null
                                }
                                else -> null
                            }
                        } as T // Cast the result to T (List<T>)
                    }
                    jsonElement.isJsonObject && T::class == JsonObject::class -> {
                        jsonElement.asJsonObject as T
                    }
                    // If T is a String and the jsonElement is a primitive string,
                    // we can return the string value
                    T::class == String::class -> {
                        jsonElement.asString as T
                    }
                    else -> {
                        // Handle other primitive types
                        when (T::class) {
                            Int::class -> jsonElement.asInt as T
                            Double::class -> jsonElement.asDouble as T
                            Long::class -> jsonElement.asLong as T
                            JsonElement::class -> jsonElement as T
                            else -> null
                        }
                    }
                }
            } else {
                null
            }
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving value for key: $key")
            null
        }
    }

}
