package com.tournament.data.golf.adapters.api.service

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import org.springframework.stereotype.Service

@Service
class TournamentApiService(
    var tournamentFactory: TournamentFactory,
    var tournamentApiCrudService: TournamentApiCrudService
) {
    fun createTournament(dataSource: DataSource, tournamentData: String): Tournament {
        val tournament = tournamentFactory.makeTournament(dataSource, tournamentData)
        return tournamentApiCrudService.save(tournament)
    }
}
