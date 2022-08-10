package com.tournament.data.golf.adapters.api.dto.tournament

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import java.text.SimpleDateFormat
import java.time.Instant

data class TournamentData1(
    val tournamentId: String,
    val tournamentName: String,
    val forecast: String,
    val courseName: String,
    val countryCode: String,
    val startDate: String,
    val endDate: String,
    val roundCount: Int
) : TournamentData() {
    var dateFormat = SimpleDateFormat("dd/MM/yy")

    override fun mapExternalId(): String {
        return tournamentId
    }

    override fun mapTournamentStartDate(): Instant {
        return dateFormat.parse(startDate).toInstant()
    }

    override fun mapTournamentEndDateDate(): Instant {
        return dateFormat.parse(endDate).toInstant()
    }

    override fun mapTournamentGolfCourseName(): String {
        return courseName
    }

    override fun mapTournamentHostCountry(): String {
        return countryCode
    }

    override fun mapTournamentNumberOfRounds(): Int {
        return roundCount
    }

    override fun addTournamentDataSource(): DataSource {
        return DataSource.DATA_SOURCE_1
    }

    override fun mapTournamentAdditionalData(): Map<String, String> {
        return mapOf("tournamentName" to tournamentName, "forecast" to forecast)
    }
}
