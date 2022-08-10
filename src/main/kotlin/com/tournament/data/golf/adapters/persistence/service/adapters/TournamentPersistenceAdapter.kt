package com.tournament.data.golf.adapters.persistence.service.adapters

import com.tournament.data.golf.adapters.persistence.mappers.TournamentPoMapper
import com.tournament.data.golf.adapters.persistence.repository.TournamentRepository
import com.tournament.data.golf.domain.model.TournamentDomain
import com.tournament.data.golf.domain.ports.secondary.TournamentPersistencePort
import org.springframework.stereotype.Repository

@Repository
class TournamentPersistenceAdapter(
    val tournamentRepository: TournamentRepository,
    val tournamentPoMapper: TournamentPoMapper
) : TournamentPersistencePort {
    override fun save(entity: TournamentDomain): TournamentDomain {
        val tournamentPo = tournamentPoMapper.toPo(entity)
        return tournamentPoMapper.toDomain(tournamentRepository.save(tournamentPo))
    }
}
