package com.tournament.data.golf.adapters.persistence.mappers

import com.tournament.data.golf.adapters.persistence.entity.DataSourcePo
import com.tournament.data.golf.adapters.persistence.entity.TournamentPo
import com.tournament.data.golf.domain.model.DataSourceDomain
import com.tournament.data.golf.domain.model.TournamentDomain
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
            tournamentDataSource = dataSourceDomainToDataSourcePo(tournamentDomain.tournamentDataSource)
//            additionalData = tournamentDomain.additionalData
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
            tournamentDataSource = dataSourcePoToDataSourceDomain(tournamentPo.tournamentDataSource)
//            additionalData = tournamentPo.additionalData
        )
    }

    override fun dataSourceDomainToDataSourcePo(dataSourceDomain: DataSourceDomain): DataSourcePo {
        return when (dataSourceDomain) {
            DataSourceDomain.DATA_SOURCE_1 -> DataSourcePo.DATA_SOURCE_1
            DataSourceDomain.DATA_SOURCE_2 -> DataSourcePo.DATA_SOURCE_2
        }
    }

    override fun dataSourcePoToDataSourceDomain(dataSourcePo: DataSourcePo): DataSourceDomain {
        return when (dataSourcePo) {
            DataSourcePo.DATA_SOURCE_1 -> DataSourceDomain.DATA_SOURCE_1
            DataSourcePo.DATA_SOURCE_2 -> DataSourceDomain.DATA_SOURCE_2
        }
    }
}
