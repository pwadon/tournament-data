package com.tournament.data.golf.domain.ports.primary

import com.tournament.data.golf.domain.model.TournamentDomain

interface TournamentCrudPort {

    fun save(entity: TournamentDomain): TournamentDomain
}
