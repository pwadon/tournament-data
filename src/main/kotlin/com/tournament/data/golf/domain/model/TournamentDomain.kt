package com.tournament.data.golf.domain.model

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import java.time.Instant

data class TournamentDomain(
    val externalId: String,
    val tournamentStartDate: Instant,
    val tournamentEndDate: Instant,
    val golfCourseName: String,
    val hostCountry: String,
    val numberOfRounds: Int,
    val tournamentDataSource: DataSource,
    val additionalData: Map<String, String>
)
