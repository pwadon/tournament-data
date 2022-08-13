package com.tournament.data.golf.unit.application.adapters

import com.tournament.data.golf.appllication.adapters.TournamentCrudAdapter
import com.tournament.data.golf.appllication.adapters.TournamentPersistenceAdapter
import com.tournament.data.golf.utils.createTournamentDomainObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
internal class TournamentCrudAdapterTest(
    @Autowired
    private var tournamentCrudAdapter: TournamentCrudAdapter
) {

    @MockBean
    private lateinit var tournamentPersistencePort: TournamentPersistenceAdapter

    private var tournamentDomain = createTournamentDomainObject()

    @Test
    fun saveShouldReturnSavedTournamentDomain_WhenSuccessful() {
        // given
        whenever(tournamentPersistencePort.save(tournamentDomain)).thenReturn(tournamentDomain)

        // when
        val tournamentDomainReceived = tournamentCrudAdapter.save(tournamentDomain)

        // then
        Assertions.assertEquals(tournamentDomain, tournamentDomainReceived)
    }

    @Test
    fun saveShouldThrowException_WhenTournamentPersistencePortFails() {
        // given
        whenever(tournamentPersistencePort.save(tournamentDomain)).thenThrow(UnsupportedOperationException::class.java)

        // then
        assertThrows<UnsupportedOperationException> {
            tournamentCrudAdapter.save(tournamentDomain)
        }
    }
}
