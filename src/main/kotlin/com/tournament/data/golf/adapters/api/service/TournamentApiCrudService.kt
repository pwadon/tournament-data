package com.tournament.data.golf.adapters.api.service

import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.adapters.api.mappers.TournamentDtoMapperImpl
import com.tournament.data.golf.domain.ports.primary.TournamentCrudPort
import org.springframework.stereotype.Service

@Service
class TournamentApiCrudService(
    var tournamentDtoMapperImpl: TournamentDtoMapperImpl,
    var tournamentCrudPort: TournamentCrudPort
) {

    public fun save(tournament: Tournament) {
        var tournamentDomain = tournamentDtoMapperImpl.toDomain(tournament)
    }
}
