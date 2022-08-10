package com.tournament.data.golf.adapters.api.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament1Dto
import java.text.SimpleDateFormat

class Tournament1Deserializer : StdDeserializer<Tournament1Dto>(Tournament1Dto::class.java) {

    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): Tournament1Dto {
        val node: JsonNode = p0!!.codec.readTree(p0)
        val dateFormat = SimpleDateFormat("dd/MM/yy")

        val externalId = node.get("tournamentId").asText()
        val tournamentStartDate = dateFormat.parse(node.get("startDate").asText()).toInstant()
        val tournamentEndDate = dateFormat.parse(node.get("endDate").asText()).toInstant()
        val golfCourseName = node.get("courseName").asText()
        val hostCountry = node.get("countryCode").asText()
        val numberOfRounds = node.get("roundCount").asInt()
        val tournamentDataSource = DataSource.DATA_SOURCE_1

        val tournamentName = node.get("tournamentName").asText()
        val forecast = node.get("forecast").asText()
        return Tournament1Dto(
            externalId,
            tournamentStartDate,
            tournamentEndDate,
            golfCourseName,
            hostCountry,
            numberOfRounds,
            tournamentDataSource,
            mapOf("tournamentName" to tournamentName, "forecast" to forecast)
        )
    }
}
