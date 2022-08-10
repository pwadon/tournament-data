package com.tournament.data.golf.adapters.persistence.mappers

import com.tournament.data.golf.adapters.persistence.entity.DataSourcePo
import com.tournament.data.golf.adapters.persistence.entity.TournamentPo
import com.tournament.data.golf.domain.model.DataSourceDomain
import com.tournament.data.golf.domain.model.TournamentDomain

interface TournamentPoMapper {

    fun toPo(tournamentDomain: TournamentDomain): TournamentPo
    fun toDomain(tournamentPo: TournamentPo): TournamentDomain
    fun dataSourceDomainToDataSourcePo(dataSourceDomain: DataSourceDomain): DataSourcePo
    fun dataSourcePoToDataSourceDomain(dataSourcePo: DataSourcePo): DataSourceDomain
}
