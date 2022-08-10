package com.tournament.data.golf.adapters.api.mappers

import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.domain.model.TournamentDomain
import org.springframework.stereotype.Component

@Component
class TournamentDtoMapperImpl : TournamentDtoMapper {
    override fun toDomain(tournament: Tournament): TournamentDomain {
        return TournamentDomain(
            externalId = tournament.externalId,
            tournamentStartDate = tournament.tournamentStartDate,
            tournamentEndDate = tournament.tournamentEndDate,
            golfCourseName = tournament.golfCourseName,
            hostCountry = tournament.hostCountry,
            numberOfRounds = tournament.numberOfRounds,
            tournamentDataSource = tournament.tournamentDataSource,
            additionalData = tournament.additionalData
        )
    }

    override fun toDto(tournamentDomain: TournamentDomain): Tournament {
        TODO("Not yet implemented")
    }
}
