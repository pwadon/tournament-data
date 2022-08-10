package com.tournament.data.golf.adapters.api.mappers

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.domain.model.DataSourceDomain
import com.tournament.data.golf.domain.model.TournamentDomain

interface TournamentDtoMapper {

    fun toDomain(tournament: Tournament): TournamentDomain
    fun toDto(tournamentDomain: TournamentDomain): Tournament
    fun dataSourceToDataSourceDomain(dataSource: DataSource): DataSourceDomain
    fun dataSourceDomainToDataSource(dataSourceDomain: DataSourceDomain): DataSource
}
