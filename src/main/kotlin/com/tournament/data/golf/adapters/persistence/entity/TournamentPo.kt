package com.tournament.data.golf.adapters.persistence.entity

import org.hibernate.annotations.Type
import java.time.Instant
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "tournament")
@Table(name = "tournament", schema = "tournaments")
data class TournamentPo(
    @Id val id: UUID,
    val externalId: String,
    val tournamentStartDate: Instant,
    val tournamentEndDate: Instant,
    val golfCourseName: String,
    val hostCountry: String,
    val numberOfRounds: Int,
    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "tournament_data_source")
    @Type(type = "data_source")
    val tournamentDataSource: DataSourcePo
//    val additionalData: Map<String, String>
)
