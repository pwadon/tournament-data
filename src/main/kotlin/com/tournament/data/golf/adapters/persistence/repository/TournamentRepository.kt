package com.tournament.data.golf.adapters.persistence.repository

import com.tournament.data.golf.adapters.persistence.entity.TournamentPo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TournamentRepository : JpaRepository<TournamentPo, UUID>
