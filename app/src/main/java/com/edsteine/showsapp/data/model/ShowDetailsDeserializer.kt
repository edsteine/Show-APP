// File: ShowDetailsDeserializer.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Deserializer for the ShowDetails model, handling JSON parsing
// Used Libraries: Google Gson

package com.edsteine.showsapp.data.model

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Deserializer for the ShowDetails model.
 */
class ShowDetailsDeserializer : JsonDeserializer<ShowDetails> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ShowDetails {
        val jsonObject = json.asJsonObject

        // Deserialize the properties from JSON, handling null cases
        val genres = jsonObject.getAsJsonArray("genres")?.map { it.asString }
        val id = jsonObject.get("id").asInt
        val image = jsonObject.get("image")?.let { context.deserialize<Image>(it, Image::class.java) }
        val language = jsonObject.get("language")?.asString
        val name = jsonObject.get("name")?.asString
        val officialSite = jsonObject.get("officialSite")?.asString
        val premiered = jsonObject.get("premiered")?.asString
        val ended = jsonObject.get("ended")?.asString
        val rating = jsonObject.get("rating")?.let { context.deserialize<Rating>(it, Rating::class.java) }
        val runtime = jsonObject.get("runtime")?.asInt
        val averageRuntime = jsonObject.get("averageRuntime")?.asInt
        val status = jsonObject.get("status")?.asString
        val summary = jsonObject.get("summary")?.asString
        val type = jsonObject.get("type")?.asString
        val weight = jsonObject.get("weight")?.asInt
        val network = jsonObject.get("network")?.let { context.deserialize<Network>(it, Network::class.java) }
        val dvdCountry = jsonObject.get("dvdCountry")?.let { context.deserialize<Country>(it, Network::class.java) }
        val webChannel = jsonObject.get("webChannel")?.let { context.deserialize<WebChannel>(it, WebChannel::class.java) }
        val externals = jsonObject.get("externals")?.let { context.deserialize<Externals>(it, Externals::class.java) }
        val schedule = jsonObject.get("schedule")?.let { context.deserialize<Schedule>(it, Schedule::class.java) }
        val updated = jsonObject.get("updated")?.asLong
        val links = jsonObject.get("links")?.let { context.deserialize<Links>(it, Links::class.java) }

        // Create and return the ShowDetails instance
        return ShowDetails(
            genres,
            id,
            image,
            language,
            name,
            officialSite,
            premiered,
            ended,
            rating,
            runtime,
            averageRuntime,
            status,
            summary,
            type,
            weight,
            network,
            webChannel,
            dvdCountry,
            externals,
            schedule,
            updated,
            links
        )
    }
}
