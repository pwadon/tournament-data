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

var externalId = "testExternalId"
var tournamentStartDate = Instant.now().minusSeconds(12000)
var tournamentEndDate = Instant.now().plusSeconds(12000)
var golfCourseName = "testCourseName"
var hostCountry = "testHostCountry"
var numberOfRounds = 10
var additionalData = mapOf("testData1" to 1, "testData2" to "testData2")

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
