package com.edsteine.showsapp.data.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class ShowItemDeserializer : JsonDeserializer<ShowItem> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ShowItem {
        val jsonObject = json.asJsonObject

        // Extract fields from the JSON object

        val id = jsonObject.get("id").asInt

        val genres = if (jsonObject.get("genres") != null && !jsonObject.get("genres").isJsonNull) {
            jsonObject.getAsJsonArray("genres")?.map { it.asString } ?: emptyList()
        } else {
            null
        }

        val language = if (jsonObject.get("language") != null && !jsonObject.get("language").isJsonNull) {
            jsonObject.get("language")?.asString
        } else {
            null
        }

        val name = if (jsonObject.get("name") != null && !jsonObject.get("name").isJsonNull) {
            jsonObject.get("name")?.asString
        } else {
            null
        }

        val officialSite = if (jsonObject.get("officialSite") != null && !jsonObject.get("officialSite").isJsonNull) {
            jsonObject.get("officialSite")?.asString
        } else {
            null
        }

        val premiered = if (jsonObject.get("premiered") != null && !jsonObject.get("premiered").isJsonNull) {
            jsonObject.get("premiered")?.asString
        } else {
            null
        }

        val runtime = if (jsonObject.get("runtime") != null && !jsonObject.get("runtime").isJsonNull) {
            jsonObject.get("runtime")?.asInt
        } else {
            null
        }

        val status = if (jsonObject.get("status") != null && !jsonObject.get("status").isJsonNull) {
            jsonObject.get("status")?.asString
        } else {
            null
        }

        val summary = if (jsonObject.get("summary") != null && !jsonObject.get("summary").isJsonNull) {
            jsonObject.get("summary")?.asString
        } else {
            null
        }

        val type = if (jsonObject.get("type") != null && !jsonObject.get("type").isJsonNull) {
            jsonObject.get("type")?.asString
        } else {
            null
        }

        val weight = if (jsonObject.get("weight") != null && !jsonObject.get("weight").isJsonNull) {
            jsonObject.get("weight")?.asInt
        } else {
            null
        }

        val updated = if (jsonObject.get("updated") != null && !jsonObject.get("updated").isJsonNull) {
            jsonObject.get("updated")?.asLong
        } else {
            null
        }



        val ended = if (jsonObject.get("ended") != null && !jsonObject.get("ended").isJsonNull) {
            jsonObject.get("ended").asString
        } else {
            null
        }

        val network = if (jsonObject.get("network") != null && !jsonObject.get("network").isJsonNull) {
            context?.deserialize<Network>(jsonObject.get("network"), Network::class.java)
        } else {
            null
        }
        val dvdCountry = if (jsonObject.get("dvdCountry") != null && !jsonObject.get("dvdCountry").isJsonNull) {
            context?.deserialize<Country>(jsonObject.get("dvdCountry"), Country::class.java)
        } else {
            null
        }

        val image = if (jsonObject.get("image") != null && !jsonObject.get("image").isJsonNull) {
            context?.deserialize<Image>(jsonObject.get("image"), Image::class.java)
        } else {
            null
        }

        val rating = if (jsonObject.get("rating") != null && !jsonObject.get("rating").isJsonNull) {
            context?.deserialize<Rating>(jsonObject.get("rating"), Rating::class.java)
        } else {
            null
        }
        val webChannel = if (jsonObject.get("webChannel") != null && !jsonObject.get("webChannel").isJsonNull) {
            context?.deserialize<WebChannel>(jsonObject.get("webChannel"), WebChannel::class.java)
        } else {
            null
        }
        val externals = if (jsonObject.get("externals") != null && !jsonObject.get("externals").isJsonNull) {
            context?.deserialize<Externals>(jsonObject.get("externals"), Externals::class.java)
        } else {
            null
        }
        val schedule = if (jsonObject.get("schedule") != null && !jsonObject.get("schedule").isJsonNull) {
            context?.deserialize<Schedule>(jsonObject.get("schedule"), Schedule::class.java)
        } else {
            null
        }
        val links = if (jsonObject.get("_links") != null && !jsonObject.get("_links").isJsonNull) {
            context?.deserialize<Links>(jsonObject.get("_links"), Links::class.java)
        } else {
            null
        }

        // Return the ShowItem object
        return ShowItem(
            genres = genres,
            id = id,
            image = image,
            language = language,
            name = name,
            officialSite = officialSite,
            premiered = premiered,
            ended = ended,
            rating = rating,
            runtime = runtime,
            status = status,
            summary = summary,
            type = type,
            weight = weight,
            network = network,
            webChannel = webChannel,
            dvdCountry = dvdCountry,
            externals = externals,
            schedule = schedule,
            updated = updated,
            links = links
        )
    }
}
