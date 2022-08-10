package com.tournament.data.golf.adapters.api.service

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import org.springframework.stereotype.Service

@Service
class TournamentApiService(
    var tournamentMapperService: TournamentMapperService
) {
    fun addTournament(dataSource: DataSource, tournamentData: String): Tournament {
        val tournament = tournamentMapperService.mapTournamentData(dataSource, tournamentData)
        return tournament
    }
}
