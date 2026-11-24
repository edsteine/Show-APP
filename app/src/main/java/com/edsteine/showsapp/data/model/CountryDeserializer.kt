/**
 * File: CountryDeserializer.kt
 * Date updated: 2024-10-16
 * Author: Your Name
 * Email: your.email@example.com
 * Phone: +1234567890
 * Project: ShowsApp
 * Description: Deserializer for the Country model, handling JSON parsing.
 * Used Libraries: Google Gson
 */

package com.edsteine.showsapp.data.model

import com.edsteine.showsapp.data.utils.AppUtils
import com.google.gson.*
import java.lang.reflect.Type

/**
 * Deserializer for the Country model.
 *
 * This class implements [JsonDeserializer] to provide custom
 * deserialization logic for the [Country] data class.
 */
class CountryDeserializer : JsonDeserializer<Country> {

    /**
     * Deserializes a JSON element into a [Country] object.
     *
     * This method extracts the country code, name, and timezone
     * from the provided JSON element, handling possible null values
     * appropriately.
     *
     * @param json The JSON element to deserialize.
     * @param typeOfT The type of the object to deserialize into.
     * @param context The context for deserialization.
     * @return The deserialized [Country] object.
     */
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Country {
        val jsonObject = json.asJsonObject

        // Use the getValueFromJson function to extract fields
        val code: String? = AppUtils.getValueFromJson(jsonObject, "code")
        val name: String? = AppUtils.getValueFromJson(jsonObject, "name")
        val timezone: String? = AppUtils.getValueFromJson(jsonObject, "timezone")

        // Return a new Country object with the extracted values
        return Country(
            code = code,
            name = name,
            timezone = timezone
        )
    }

}
