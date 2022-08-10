package com.tournament.data.golf.adapters.api.service

import com.tournament.data.golf.adapters.api.dto.tournament.TournamentDto
import com.tournament.data.golf.adapters.api.mappers.TournamentDtoMapperImpl
import com.tournament.data.golf.domain.ports.primary.TournamentCrudPort
import org.springframework.stereotype.Service

@Service
class TournamentApiCrudService(
    var tournamentDtoMapperImpl: TournamentDtoMapperImpl,
    var tournamentCrudPort: TournamentCrudPort
) {

    fun save(tournament: TournamentDto): TournamentDto {
        val tournamentDomain = tournamentDtoMapperImpl.toDomain(tournament)
        return tournamentDtoMapperImpl.toDto(tournamentCrudPort.save(tournamentDomain))
    }
}
