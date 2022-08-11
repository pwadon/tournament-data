package com.tournament.data.golf.adapters.api.dto.tournament

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import java.time.Instant

data class TournamentData2(
    val tournamentUUID: String,
    val golfCourse: String,
    val competitionName: String,
    val hostCountry: String,
    val epochStart: Long,
    val epochFinish: Long,
    val rounds: Int,
    val playerCount: Int
) : TournamentData() {
    override fun mapExternalId(): String {
        return tournamentUUID
    }

    override fun mapTournamentStartDate(): Instant {
        return Instant.ofEpochSecond(epochStart)
    }

    override fun mapTournamentEndDateDate(): Instant {
        return Instant.ofEpochSecond(epochFinish)
    }

    override fun mapTournamentGolfCourseName(): String {
        return golfCourse
    }

    override fun mapTournamentHostCountry(): String {
        return hostCountry
    }

    override fun mapTournamentNumberOfRounds(): Int {
        return rounds
    }

    override fun addTournamentDataSource(): DataSource {
        return DataSource.DATA_SOURCE_2
    }

    override fun mapTournamentAdditionalData(): Map<String, Any> {
        return mapOf("competitionName" to competitionName, "playerCount" to playerCount.toString())
    }
}
