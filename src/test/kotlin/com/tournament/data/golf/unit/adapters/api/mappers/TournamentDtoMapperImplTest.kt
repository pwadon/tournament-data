package com.tournament.data.golf.unit.adapters.api.mappers

import com.tournament.data.golf.adapters.api.mappers.TournamentDtoMapperImpl
import com.tournament.data.golf.utils.createTournamentDomainObject
import com.tournament.data.golf.utils.createTournamentDtoObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean

@SpringBootTest
internal class TournamentDtoMapperImplTest {

    @SpyBean
    private lateinit var tournamentDtoMapperImpl: TournamentDtoMapperImpl

    private val tournamentDomain = createTournamentDomainObject()

    private val tournamentDto = createTournamentDtoObject()

    @Test
    fun toDomainShouldReturnTournamentDomainObjectWithCorrectData() {
        // when
        val tournamentDomain = tournamentDtoMapperImpl.toDomain(tournamentDto)

        // then
        assertAll(
            "tournamentDomain",
            { assertEquals(tournamentDto.externalId, tournamentDomain.externalId) },
            { assertEquals(tournamentDto.tournamentStartDate, tournamentDomain.tournamentStartDate) },
            { assertEquals(tournamentDto.tournamentEndDate, tournamentDomain.tournamentEndDate) },
            { assertEquals(tournamentDto.golfCourseName, tournamentDomain.golfCourseName) },
            { assertEquals(tournamentDto.hostCountry, tournamentDomain.hostCountry) },
            { assertEquals(tournamentDto.numberOfRounds, tournamentDomain.numberOfRounds) },
            { assertEquals(tournamentDto.tournamentDataSource.name, tournamentDomain.tournamentDataSource.name) },
            { assertEquals(tournamentDto.additionalData, tournamentDomain.additionalData) }
        )
    }

    @Test
    fun toDomainShouldThrowException_WhenToDomainFails() {
        // given
        whenever(tournamentDtoMapperImpl.toDomain(tournamentDto)).thenThrow(TypeCastException::class.java)

        // then
        assertThrows<TypeCastException> {
            tournamentDtoMapperImpl.toDomain(tournamentDto)
        }
    }

    @Test
    fun toDtoShouldReturnTournamentDtoObjectWithCorrectData() {
        // when
        val tournamentDto = tournamentDtoMapperImpl.toDto(tournamentDomain)

        // then
        assertAll(
            "tournamentDto",
            { assertEquals(tournamentDomain.externalId, tournamentDto.externalId) },
            { assertEquals(tournamentDomain.tournamentStartDate, tournamentDto.tournamentStartDate) },
            { assertEquals(tournamentDomain.tournamentEndDate, tournamentDto.tournamentEndDate) },
            { assertEquals(tournamentDomain.golfCourseName, tournamentDto.golfCourseName) },
            { assertEquals(tournamentDomain.hostCountry, tournamentDto.hostCountry) },
            { assertEquals(tournamentDomain.numberOfRounds, tournamentDto.numberOfRounds) },
            { assertEquals(tournamentDomain.tournamentDataSource.name, tournamentDto.tournamentDataSource.name) },
            { assertEquals(tournamentDomain.additionalData, tournamentDto.additionalData) }
        )
    }

    @Test
    fun toDtoShouldThrowException_WhenToDtoFails() {
        // given
        whenever(tournamentDtoMapperImpl.toDto(tournamentDomain)).thenThrow(TypeCastException::class.java)

        // then
        assertThrows<TypeCastException> {
            tournamentDtoMapperImpl.toDto(tournamentDomain)
        }
    }
}
