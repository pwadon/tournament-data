package com.tournament.data.golf.adapters.api.dto.tournament

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import java.time.Instant

data class TournamentDto(
    override val externalId: String,
    override val tournamentStartDate: Instant,
    override val tournamentEndDate: Instant,
    override val golfCourseName: String,
    override val hostCountry: String,
    override val numberOfRounds: Int,
    override val tournamentDataSource: DataSource,
    override val additionalData: Map<String, String>
) : Tournament
