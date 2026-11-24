package com.edsteine.showsapp.data.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class WebChannelDeserializer : JsonDeserializer<WebChannel> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): WebChannel {
        val jsonObject = json.asJsonObject

        // Safely extract fields from the JSON object
        val id = jsonObject.get("id")?.takeIf { !it.isJsonNull }?.asInt
        val name = jsonObject.get("name")?.takeIf { !it.isJsonNull }?.asString

        val country = if (jsonObject.get("country") != null && !jsonObject.get("country").isJsonNull) {
            context?.deserialize<Country>(jsonObject.get("country"), Country::class.java)
        } else {
            null
        }
        val officialSite = jsonObject.get("officialSite")?.takeIf { !it.isJsonNull }?.asString

        // Return the WebChannel object
        return WebChannel(
            id = id,
            name = name,
            country = country,
            officialSite = officialSite
        )
    }
}
