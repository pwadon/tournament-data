package com.tournament.data.golf.adapters.api.service

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.adapters.api.dto.tournament.TournamentData1
import com.tournament.data.golf.adapters.api.dto.tournament.TournamentData2
import org.springframework.stereotype.Service

@Service
class TournamentAdapter {
    private val mapper = jacksonObjectMapper().registerModule(JavaTimeModule())

    fun makeTournament(dataSource: DataSource, tournamentData: String): Tournament {
        return when (dataSource) {
            DataSource.DATA_SOURCE_1 -> mapper.readValue(tournamentData, TournamentData1::class.java).createTournament()
            DataSource.DATA_SOURCE_2 -> mapper.readValue(tournamentData, TournamentData2::class.java).createTournament()
        }
    }
}
