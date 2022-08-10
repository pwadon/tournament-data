package com.tournament.data.golf.domain.model

import java.time.Instant

data class TournamentDomain(
    val externalId: String,
    val tournamentStartDate: Instant,
    val tournamentEndDate: Instant,
    val golfCourseName: String,
    val hostCountry: String,
    val numberOfRounds: Int,
    val tournamentDataSource: DataSourceDomain
//    val additionalData: Map<String, String>
)
