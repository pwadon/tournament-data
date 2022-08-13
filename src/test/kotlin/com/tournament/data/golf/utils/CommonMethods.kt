package com.tournament.data.golf.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.TournamentDto
import com.tournament.data.golf.adapters.persistence.entity.DataSourcePo
import com.tournament.data.golf.adapters.persistence.entity.TournamentPo
import com.tournament.data.golf.domain.model.DataSourceDomain
import com.tournament.data.golf.domain.model.TournamentDomain
import java.time.Instant
import java.util.UUID

var externalId = "174638"
var tournamentStartDate: Instant = Instant.now().minusSeconds(12000)
var tournamentEndDate: Instant = Instant.now().plusSeconds(12000)
var golfCourseName = "Sunnydale Golf Course"
var hostCountry = "United States Of America"
var numberOfRounds = 10
var additionalData = mapOf("testData1" to 1, "testData2" to "testData2")
var tournamentId = "174638"
var tournamentName = "Women's Open Championship"
var forecast = "fair"
var courseName = "Sunnydale Golf Course"
var countryCode = "GB"
var startDate = "09/07/21"
var endDate = "13/07/21"
var roundCount = "4"
var tournamentUUID = "87fc6650-e114-4179-9aef-6a9a13030f80"
var golfCourse = "Happy Days Golf Club"
var competitionName = "South West Invitational"
var epochStart = "1638349200"
var epochFinish = "1638468000"
var rounds = "2"
var playerCount = "35"

fun createTournamentDomainObject(): TournamentDomain {
    return TournamentDomain(
        externalId = externalId,
        tournamentStartDate = tournamentStartDate,
        tournamentEndDate = tournamentEndDate,
        golfCourseName = golfCourseName,
        hostCountry = hostCountry,
        numberOfRounds = numberOfRounds,
        tournamentDataSource = DataSourceDomain.DATA_SOURCE_1,
        additionalData = additionalData
    )
}

fun createTournamentDtoObject(): TournamentDto {
    return TournamentDto(
        externalId = externalId,
        tournamentStartDate = tournamentStartDate,
        tournamentEndDate = tournamentEndDate,
        golfCourseName = golfCourseName,
        hostCountry = hostCountry,
        numberOfRounds = numberOfRounds,
        tournamentDataSource = DataSource.DATA_SOURCE_1,
        additionalData = additionalData
    )
}

fun createTournamentPoObject(): TournamentPo {
    return TournamentPo(
        id = UUID.randomUUID(),
        externalId = externalId,
        tournamentStartDate = tournamentStartDate,
        tournamentEndDate = tournamentEndDate,
        golfCourseName = golfCourseName,
        hostCountry = hostCountry,
        numberOfRounds = numberOfRounds,
        tournamentDataSource = DataSourcePo.DATA_SOURCE_1,
        additionalData = jacksonObjectMapper().writeValueAsString(additionalData)
    )
}

fun createDataSource1PayloadString(startDate: String, endDate: String, roundCount: String): String {
    return "{\n" +
        "\t\"tournamentId\": \"$tournamentId\",\n" +
        "\t\"tournamentName\": \"$tournamentName\",\n" +
        "\t\"forecast\": \"$forecast\",\n" +
        "\t\"courseName\": \"$courseName\",\n" +
        "\t\"countryCode\": \"$countryCode\",\n" +
        "\t\"startDate\": \"$startDate\",\n" +
        "\t\"endDate\": \"$endDate\",\n" +
        "\t\"roundCount\": \"$roundCount\"\n" +
        "}"
}

fun createDataSource2PayloadString(
    tournamentUUID: String,
    epochStart: String,
    epochFinish: String,
    rounds: String
): String {
    return "{\n" +
        "    \"tournamentUUID\":\"$tournamentUUID\",\n" +
        "    \"golfCourse\":\"$golfCourse\",\n" +
        "    \"competitionName\":\"$competitionName\",\n" +
        "    \"hostCountry\":\"$hostCountry\",\n" +
        "    \"epochStart\":\"$epochStart\",\n" +
        "    \"epochFinish\":\"$epochFinish\",\n" +
        "    \"rounds\":\"$rounds\",\n" +
        "    \"playerCount\":\"$playerCount\"\n" +
        "}"
}
