package com.tournament.data.golf.adapters.api.service

import com.tournament.data.golf.adapters.api.mappers.TournamentDtoMapperImpl
import com.tournament.data.golf.domain.ports.primary.TournamentCrudPort
import com.tournament.data.golf.utils.createTournamentDomainObject
import com.tournament.data.golf.utils.createTournamentDtoObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
internal class TournamentApiCrudServiceTest(
    @Autowired
    private var tournamentApiCrudService: TournamentApiCrudService
) {

    @MockBean
    private lateinit var tournamentDtoMapperImpl: TournamentDtoMapperImpl

    @MockBean
    private lateinit var tournamentCrudPort: TournamentCrudPort

    private val tournamentDto = createTournamentDtoObject()

    private val tournamentDomain = createTournamentDomainObject()

    @Test
    fun saveTournamentShouldReturnSavedTournament_WhenSuccessful() {
        // given
        whenever(tournamentDtoMapperImpl.toDomain(tournamentDto)).thenReturn(tournamentDomain)
        whenever(tournamentDtoMapperImpl.toDto(tournamentDomain)).thenReturn(tournamentDto)
        whenever(tournamentCrudPort.save(tournamentDomain)).thenReturn(tournamentDomain)

        // when
        val tournamentDomainReceived = tournamentApiCrudService.save(tournamentDto)

        // then
        assertEquals(tournamentDto, tournamentDomainReceived)
    }

    @Test
    fun saveTournamentShouldThrowException_WhenDtoMapperFails() {
        // given
        whenever(tournamentDtoMapperImpl.toDomain(tournamentDto)).thenReturn(tournamentDomain)
        whenever(tournamentDtoMapperImpl.toDto(tournamentDomain)).thenThrow(ClassCastException::class.java)
        whenever(tournamentCrudPort.save(tournamentDomain)).thenReturn(tournamentDomain)

        // then
        assertThrows<ClassCastException> {
            tournamentApiCrudService.save(tournamentDto)
        }
    }

    @Test
    fun saveTournamentShouldThrowException_WhenTournamentCrudPortFails() {
        // given
        whenever(tournamentDtoMapperImpl.toDomain(tournamentDto)).thenReturn(tournamentDomain)
        whenever(tournamentDtoMapperImpl.toDto(tournamentDomain)).thenReturn(tournamentDto)
        whenever(tournamentCrudPort.save(tournamentDomain)).thenThrow(UnsupportedOperationException::class.java)

        // then
        assertThrows<UnsupportedOperationException> {
            tournamentApiCrudService.save(tournamentDto)
        }
    }
}
