package com.tournament.data.golf.appllication.adapters

import com.tournament.data.golf.adapters.persistence.mappers.TournamentPoMapper
import com.tournament.data.golf.adapters.persistence.repository.TournamentRepository
import com.tournament.data.golf.utils.createTournamentDomainObject
import com.tournament.data.golf.utils.createTournamentPoObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
internal class TournamentPersistenceAdapterTest(
    @Autowired
    private var tournamentPersistenceAdapter: TournamentPersistenceAdapter
) {

    @MockBean
    private lateinit var tournamentRepository: TournamentRepository

    @MockBean
    private lateinit var tournamentPoMapper: TournamentPoMapper

    private val tournamentDomain = createTournamentDomainObject()

    private val tournamentPo = createTournamentPoObject()

    @Test
    fun saveShouldReturnSavedTournament_WhenSuccessful() {
        // given
        whenever(tournamentPoMapper.toPo(tournamentDomain)).thenReturn(tournamentPo)
        whenever(tournamentPoMapper.toDomain(tournamentPo)).thenReturn(tournamentDomain)
        whenever(tournamentRepository.save(tournamentPo)).thenReturn(tournamentPo)

        // when
        val tournamentDomainReceived = tournamentPersistenceAdapter.save(tournamentDomain)

        // then
        assertEquals(tournamentDomain, tournamentDomainReceived)
    }

    @Test
    fun saveShouldThrowException_WhenTournamentPoMapperFails() {
        // given
        whenever(tournamentPoMapper.toPo(tournamentDomain)).thenReturn(tournamentPo)
        whenever(tournamentPoMapper.toDomain(tournamentPo)).thenThrow(ClassCastException::class.java)
        whenever(tournamentRepository.save(tournamentPo)).thenReturn(tournamentPo)

        // then
        assertThrows<ClassCastException> {
            tournamentPersistenceAdapter.save(tournamentDomain)
        }
    }

    @Test
    fun saveShouldThrowException_WhenTournamentRepositoryFails() {
        // given
        whenever(tournamentPoMapper.toPo(tournamentDomain)).thenReturn(tournamentPo)
        whenever(tournamentPoMapper.toDomain(tournamentPo)).thenReturn(tournamentDomain)
        whenever(tournamentRepository.save(tournamentPo)).thenThrow(UnsupportedOperationException::class.java)

        // then
        assertThrows<UnsupportedOperationException> {
            tournamentPersistenceAdapter.save(tournamentDomain)
        }
    }
}
