package com.tournament.data.golf.adapters.api.service

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament1Dto
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament2Dto
import org.springframework.stereotype.Service

@Service
class TournamentMapperService {

    fun mapTournamentData(dataSource: DataSource, tournamentData: String): Tournament {
        val mapper = jacksonObjectMapper()
        mapper.registerModule(JavaTimeModule())

        val tournament: Tournament = if (dataSource == DataSource.DATA_SOURCE_1) {
            mapper.readValue(tournamentData, Tournament1Dto::class.java)
        } else {
            mapper.readValue(tournamentData, Tournament2Dto::class.java)
        }
        return tournament
    }
}
