package com.tournament.data.golf.unit.adapters.mappers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tournament.data.golf.adapters.persistence.mappers.TournamentPoMapperImpl
import com.tournament.data.golf.utils.createTournamentDomainObject
import com.tournament.data.golf.utils.createTournamentPoObject
import org.json.JSONObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.whenever
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.SpyBean

@WebMvcTest(TournamentPoMapperImplTest::class)
internal class TournamentPoMapperImplTest {

    @SpyBean
    private lateinit var tournamentPoMapperImpl: TournamentPoMapperImpl

    private val tournamentDomain = createTournamentDomainObject()

    private val tournamentPo = createTournamentPoObject()

    @Test
    fun toPoShouldReturnTournamentPoObjectWithCorrectData() {
        // when
        val tournamentPo = tournamentPoMapperImpl.toPo(tournamentDomain)

        // then
        assertAll(
            "tournamentDomain",
            { assertEquals(tournamentDomain.externalId, tournamentPo.externalId) },
            { assertEquals(tournamentDomain.tournamentStartDate, tournamentPo.tournamentStartDate) },
            { assertEquals(tournamentDomain.tournamentEndDate, tournamentPo.tournamentEndDate) },
            { assertEquals(tournamentDomain.golfCourseName, tournamentPo.golfCourseName) },
            { assertEquals(tournamentDomain.hostCountry, tournamentPo.hostCountry) },
            { assertEquals(tournamentDomain.numberOfRounds, tournamentPo.numberOfRounds) },
            { assertEquals(tournamentDomain.tournamentDataSource.name, tournamentPo.tournamentDataSource.name) },
            {
                assertEquals(
                    jacksonObjectMapper().writeValueAsString(tournamentDomain.additionalData),
                    tournamentPo.additionalData
                )
            }
        )
    }

    @Test
    fun toPoShouldThrowException_WhenToPoFails() {
        // given
        whenever(tournamentPoMapperImpl.toPo(tournamentDomain)).thenThrow(TypeCastException::class.java)

        // then
        assertThrows<TypeCastException> {
            tournamentPoMapperImpl.toPo(tournamentDomain)
        }
    }

    @Test
    fun toDomainShouldReturnTournamentDomainObjectWithCorrectData() {
        // when
        val tournamentDomain = tournamentPoMapperImpl.toDomain(tournamentPo)

        // then
        assertAll(
            "tournamentDomain",
            { assertEquals(tournamentPo.externalId, tournamentDomain.externalId) },
            { assertEquals(tournamentPo.tournamentStartDate, tournamentDomain.tournamentStartDate) },
            { assertEquals(tournamentPo.tournamentEndDate, tournamentDomain.tournamentEndDate) },
            { assertEquals(tournamentPo.golfCourseName, tournamentDomain.golfCourseName) },
            { assertEquals(tournamentPo.hostCountry, tournamentDomain.hostCountry) },
            { assertEquals(tournamentPo.numberOfRounds, tournamentDomain.numberOfRounds) },
            { assertEquals(tournamentPo.tournamentDataSource.name, tournamentDomain.tournamentDataSource.name) },
            { assertEquals(JSONObject(tournamentPo.additionalData).toMap(), tournamentDomain.additionalData) }
        )
    }

    @Test
    fun toDomainShouldThrowException_WhenToDomainFails() {
        // given
        whenever(tournamentPoMapperImpl.toDomain(tournamentPo)).thenThrow(TypeCastException::class.java)

        // then
        assertThrows<TypeCastException> {
            tournamentPoMapperImpl.toDomain(tournamentPo)
        }
    }
}
