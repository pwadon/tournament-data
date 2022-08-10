package com.tournament.data.golf.domain.ports.secondary

import com.tournament.data.golf.domain.model.TournamentDomain

interface TournamentPersistencePort {

    fun save(entity: TournamentDomain): TournamentDomain
}
