/**
 * File: NetworkDeserializer.kt
 * Date updated: 2024-10-16
 * Author: Your Name
 * Email: your.email@example.com
 * Phone: +1234567890
 * Project: ShowsApp
 * Description:
 * Deserializer for the Network model, handling JSON parsing.
 * This class is responsible for converting JSON data into
 * a Network object, which includes information about the
 * broadcasting network, including its country of origin.
 * Used Libraries: Google Gson, Timber
 */

package com.edsteine.showsapp.data.model

import com.google.gson.*
import java.lang.reflect.Type
import timber.log.Timber

/**
 * Deserializer for the Network model.
 *
 * This class implements JsonDeserializer to convert JSON data
 * into Network objects. It retrieves country information
 * as well as the network's ID and name.
 */
class NetworkDeserializer : JsonDeserializer<Network> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Network {
        val jsonObject = json.asJsonObject

        // Extract the country object
        val countryJson = jsonObject.getAsJsonObject("country")

        // Safely extract country details with null checks and logging
        val countryCode = try {
            if (countryJson.has("countryCode") && !countryJson.get("countryCode").isJsonNull) {
                countryJson.get("countryCode").asString
            } else null
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving countryCode")
            null
        }

        val countryName = try {
            if (countryJson.has("countryName") && !countryJson.get("countryName").isJsonNull) {
                countryJson.get("countryName").asString
            } else null
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving countryName")
            null
        }

        val timeZone = try {
            if (countryJson.has("timeZone") && !countryJson.get("timeZone").isJsonNull) {
                countryJson.get("timeZone").asString
            } else null
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving timeZone")
            null
        }

        // Create a Country instance
        val country = Country(countryCode, countryName, timeZone)

        // Extract other properties with error handling
        val id = try {
            if (jsonObject.has("id") && !jsonObject.get("id").isJsonNull) {
                jsonObject.get("id").asInt
            } else null
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving id")
            null
        }

        val name = try {
            if (jsonObject.has("name") && !jsonObject.get("name").isJsonNull) {
                jsonObject.get("name").asString
            } else null
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving name")
            null
        }

        // Create and return the Network instance
        return Network(country, id, name)
    }
}
