package com.tournament.data.golf.adapters.api.dto.tournament

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.tournament.data.golf.adapters.api.deserializers.Tournament2Deserializer
import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = Tournament2Deserializer::class)
class Tournament2Dto(
    override val externalId: String,
    override val tournamentStartDate: Instant,
    override val tournamentEndDate: Instant,
    override val golfCourseName: String,
    override val hostCountry: String,
    override val numberOfRounds: Int,
    override val tournamentDataSource: DataSource,
    override val additionalData: Map<String, String>
) : Tournament
