package com.tournament.data.golf.adapters.persistence.mappers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tournament.data.golf.adapters.persistence.entity.DataSourcePo
import com.tournament.data.golf.adapters.persistence.entity.TournamentPo
import com.tournament.data.golf.domain.model.DataSourceDomain
import com.tournament.data.golf.domain.model.TournamentDomain
import org.json.JSONObject
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class TournamentPoMapperImpl : TournamentPoMapper {
    override fun toPo(tournamentDomain: TournamentDomain): TournamentPo {
        return TournamentPo(
            id = UUID.randomUUID(),
            externalId = tournamentDomain.externalId,
            tournamentStartDate = tournamentDomain.tournamentStartDate,
            tournamentEndDate = tournamentDomain.tournamentEndDate,
            golfCourseName = tournamentDomain.golfCourseName,
            hostCountry = tournamentDomain.hostCountry,
            numberOfRounds = tournamentDomain.numberOfRounds,
            tournamentDataSource = DataSourcePo.valueOf(tournamentDomain.tournamentDataSource.name),
            additionalData = jacksonObjectMapper().writeValueAsString(tournamentDomain.additionalData)
        )
    }

    override fun toDomain(tournamentPo: TournamentPo): TournamentDomain {
        return TournamentDomain(
            externalId = tournamentPo.externalId,
            tournamentStartDate = tournamentPo.tournamentStartDate,
            tournamentEndDate = tournamentPo.tournamentEndDate,
            golfCourseName = tournamentPo.golfCourseName,
            hostCountry = tournamentPo.hostCountry,
            numberOfRounds = tournamentPo.numberOfRounds,
            tournamentDataSource = DataSourceDomain.valueOf(tournamentPo.tournamentDataSource.name),
            additionalData = JSONObject(tournamentPo.additionalData).toMap()
        )
    }
}
