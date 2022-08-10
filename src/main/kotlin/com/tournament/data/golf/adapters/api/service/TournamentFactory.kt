package com.tournament.data.golf.adapters.api.service

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.TournamentData1
import com.tournament.data.golf.adapters.api.dto.tournament.TournamentData2
import com.tournament.data.golf.adapters.api.dto.tournament.TournamentDto
import org.springframework.stereotype.Service

@Service
class TournamentFactory {
    private val mapper = jacksonObjectMapper().registerModule(JavaTimeModule())

    fun makeTournament(dataSource: DataSource, tournamentData: String): TournamentDto {
        val tournament: TournamentDto = if (dataSource == DataSource.DATA_SOURCE_1) {
            mapper.readValue(tournamentData, TournamentData1::class.java).createTournament()
        } else {
            mapper.readValue(tournamentData, TournamentData2::class.java).createTournament()
        }
        return tournament
    }
}
