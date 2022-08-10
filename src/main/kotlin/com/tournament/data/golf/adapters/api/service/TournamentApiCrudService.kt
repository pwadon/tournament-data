package com.tournament.data.golf.adapters.api.service

import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.adapters.api.mappers.TournamentDtoMapper
import com.tournament.data.golf.domain.ports.primary.TournamentCrudPort

class TournamentApiCrudService(
    var tournamentDtoMapper: TournamentDtoMapper,
    var tournamentCrudPort: TournamentCrudPort
) {

    public fun save(tournament: Tournament) {
        var tournamentDomain = tournamentDtoMapper.toDomain(tournament)
    }
}
