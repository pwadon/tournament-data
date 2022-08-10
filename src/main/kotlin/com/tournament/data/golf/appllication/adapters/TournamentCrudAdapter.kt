package com.tournament.data.golf.appllication.adapters

import com.tournament.data.golf.domain.model.TournamentDomain
import com.tournament.data.golf.domain.ports.primary.TournamentCrudPort

class TournamentCrudAdapter(
//    var tournamentPersistencePort: TournamentPersistencePort
) : TournamentCrudPort {

    override fun save(entity: TournamentDomain): TournamentDomain {
        TODO("Not yet implemented")
    }
}
