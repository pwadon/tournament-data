package com.tournament.data.golf.adapters.persistence.mappers

import com.tournament.data.golf.adapters.persistence.entity.TournamentPo
import com.tournament.data.golf.domain.model.TournamentDomain

interface TournamentPoMapper {

    fun toPo(tournamentDomain: TournamentDomain): TournamentPo
    fun toDomain(tournamentPo: TournamentPo): TournamentDomain
}
