package com.tournament.data.golf.adapters.api.mappers

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.adapters.api.dto.tournament.TournamentDto
import com.tournament.data.golf.domain.model.DataSourceDomain
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
            tournamentDataSource = DataSourceDomain.valueOf(tournament.tournamentDataSource.name),
            additionalData = tournament.additionalData
        )
    }

    override fun toDto(tournamentDomain: TournamentDomain): TournamentDto {
        return TournamentDto(
            externalId = tournamentDomain.externalId,
            tournamentStartDate = tournamentDomain.tournamentStartDate,
            tournamentEndDate = tournamentDomain.tournamentEndDate,
            golfCourseName = tournamentDomain.golfCourseName,
            hostCountry = tournamentDomain.hostCountry,
            numberOfRounds = tournamentDomain.numberOfRounds,
            tournamentDataSource = DataSource.valueOf(tournamentDomain.tournamentDataSource.name),
            additionalData = tournamentDomain.additionalData
        )
    }
}
