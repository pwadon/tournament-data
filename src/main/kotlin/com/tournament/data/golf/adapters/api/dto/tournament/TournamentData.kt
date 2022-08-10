package com.tournament.data.golf.adapters.api.dto.tournament

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import java.time.Instant

abstract class TournamentData {

    fun createTournament(): TournamentDto {
        return TournamentDto(
            externalId = mapExternalId(),
            tournamentStartDate = mapTournamentStartDate(),
            tournamentEndDate = mapTournamentEndDateDate(),
            golfCourseName = mapTournamentGolfCourseName(),
            hostCountry = mapTournamentHostCountry(),
            numberOfRounds = mapTournamentNumberOfRounds(),
            tournamentDataSource = addTournamentDataSource()
//            additionalData = mapTournamentAdditionalData()
        )
    }
    abstract fun mapExternalId(): String
    abstract fun mapTournamentStartDate(): Instant
    abstract fun mapTournamentEndDateDate(): Instant
    abstract fun mapTournamentGolfCourseName(): String
    abstract fun mapTournamentHostCountry(): String
    abstract fun mapTournamentNumberOfRounds(): Int
    abstract fun addTournamentDataSource(): DataSource
    abstract fun mapTournamentAdditionalData(): Map<String, String>
}
