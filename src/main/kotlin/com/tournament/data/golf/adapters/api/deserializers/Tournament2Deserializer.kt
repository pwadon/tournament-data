package com.tournament.data.golf.adapters.api.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament2Dto
import java.time.Instant

class Tournament2Deserializer : StdDeserializer<Tournament2Dto>(Tournament2Dto::class.java) {

    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): Tournament2Dto {
        val node: JsonNode = p0!!.codec.readTree(p0)

        val externalId = node.get("tournamentUUID").asText()
        val tournamentStartDate = Instant.ofEpochMilli(node.get("epochStart").asLong() * 1000)
        val tournamentEndDate = Instant.ofEpochMilli(node.get("epochFinish").asLong() * 1000)
        val golfCourseName = node.get("golfCourse").asText()
        val hostCountry = node.get("hostCountry").asText()
        val numberOfRounds = node.get("rounds").asInt()
        val tournamentDataSource = DataSource.DATA_SOURCE_2

        val competitionName = node.get("competitionName").asText()
        val playerCount = node.get("playerCount").asText()
        return Tournament2Dto(
            externalId,
            tournamentStartDate,
            tournamentEndDate,
            golfCourseName,
            hostCountry,
            numberOfRounds,
            tournamentDataSource,
            mapOf("competitionName" to competitionName, "playerCount" to playerCount)
        )
    }
}
