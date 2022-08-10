package com.tournament.data.golf.appllication.adapters

import com.tournament.data.golf.domain.model.TournamentDomain
import com.tournament.data.golf.domain.ports.primary.TournamentCrudPort
import com.tournament.data.golf.domain.ports.secondary.TournamentPersistencePort
import org.springframework.stereotype.Component

@Component
class TournamentCrudAdapter(
    var tournamentPersistencePort: TournamentPersistencePort
) : TournamentCrudPort {

    override fun save(entity: TournamentDomain): TournamentDomain {
        return tournamentPersistencePort.save(entity)
    }
}
