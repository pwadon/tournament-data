package com.tournament.data.golf.adapters.api.service

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.utils.createTournamentDtoObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
internal class TournamentApiServiceTest(
    @Autowired
    private var tournamentApiService: TournamentApiService
) {

    @MockBean
    private lateinit var tournamentAdapter: TournamentAdapter

    @MockBean
    private lateinit var tournamentApiCrudService: TournamentApiCrudService

    private val tournamentDto = createTournamentDtoObject()

    private val tournamentData = "true"

    private val dataSource = DataSource.DATA_SOURCE_1

    @Test
    fun createTournamentShouldReturnSavedTournament_WhenSuccessful() {
        // given
        whenever(tournamentAdapter.makeTournament(dataSource, tournamentData)).thenReturn(tournamentDto)
        whenever(tournamentApiCrudService.save(tournamentDto)).thenReturn(tournamentDto)

        // when
        val tournamentDtoReceived = tournamentApiService.createTournament(dataSource, tournamentData)

        // then
        Assertions.assertEquals(tournamentDto, tournamentDtoReceived)
    }

    @Test
    fun createTournamentShouldThrowException_WhenTournamentAdapterFails() {
        // given
        whenever(tournamentAdapter.makeTournament(dataSource, tournamentData)).thenThrow(ClassCastException::class.java)
        whenever(tournamentApiCrudService.save(tournamentDto)).thenReturn(tournamentDto)

        // then
        assertThrows<ClassCastException> {
            tournamentApiService.createTournament(dataSource, tournamentData)
        }
    }

    @Test
    fun createTournamentShouldThrowException_WhenTournamentApiCrudServiceFails() {
        // given
        whenever(tournamentAdapter.makeTournament(dataSource, tournamentData)).thenReturn(tournamentDto)
        whenever(tournamentApiCrudService.save(tournamentDto)).thenThrow(UnsupportedOperationException::class.java)

        // then
        assertThrows<UnsupportedOperationException> {
            tournamentApiService.createTournament(dataSource, tournamentData)
        }
    }
}
