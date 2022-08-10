package com.tournament.data.golf.adapters.api.mappers

import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.domain.model.TournamentDomain

interface TournamentDtoMapperInterface {

    fun toDomain(tournament: Tournament): TournamentDomain
    fun toDto(tournamentDomain: TournamentDomain): Tournament
}
