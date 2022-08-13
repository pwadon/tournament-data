package com.tournament.data.golf.unit.adapters.api.service

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.service.TournamentAdapter
import com.tournament.data.golf.utils.competitionName
import com.tournament.data.golf.utils.countryCode
import com.tournament.data.golf.utils.courseName
import com.tournament.data.golf.utils.createDataSource1PayloadString
import com.tournament.data.golf.utils.createDataSource2PayloadString
import com.tournament.data.golf.utils.endDate
import com.tournament.data.golf.utils.epochFinish
import com.tournament.data.golf.utils.epochStart
import com.tournament.data.golf.utils.forecast
import com.tournament.data.golf.utils.golfCourse
import com.tournament.data.golf.utils.hostCountry
import com.tournament.data.golf.utils.playerCount
import com.tournament.data.golf.utils.roundCount
import com.tournament.data.golf.utils.rounds
import com.tournament.data.golf.utils.startDate
import com.tournament.data.golf.utils.tournamentId
import com.tournament.data.golf.utils.tournamentName
import com.tournament.data.golf.utils.tournamentUUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.IllegalArgumentException
import java.text.ParseException
import java.time.Instant

@SpringBootTest
internal class TournamentAdapterTest(
    @Autowired
    var tournamentAdapter: TournamentAdapter
) {

    private var dataSource1 = DataSource.DATA_SOURCE_1

    private var dataSource2 = DataSource.DATA_SOURCE_2

    private var startDateInstant = Instant.parse("2021-07-08T22:00:00Z")
    private var endDateInstant = Instant.parse("2021-07-12T22:00:00Z")
    private var epochStartInstant = Instant.ofEpochSecond(1638349200)
    private var epochEndInstant = Instant.ofEpochSecond(1638468000)

    @Test
    fun makeTournamentShouldCreateTournamentFromDataSource1() {
        // given
        val tournamentData1Payload = createDataSource1PayloadString(startDate, endDate, roundCount)

        // when
        val tournament = tournamentAdapter.makeTournament(dataSource1, tournamentData1Payload)

        // then
        assertAll(
            "tournament",
            { assertEquals(tournamentId, tournament.externalId) },
            { assertEquals(startDateInstant, tournament.tournamentStartDate) },
            { assertEquals(endDateInstant, tournament.tournamentEndDate) },
            { assertEquals(courseName, tournament.golfCourseName) },
            { assertEquals(countryCode, tournament.hostCountry) },
            { assertEquals(Integer.parseInt(roundCount), tournament.numberOfRounds) },
            { assertEquals(dataSource1, tournament.tournamentDataSource) },
            {
                assertEquals(
                    mapOf(
                        "forecast" to forecast,
                        "tournamentName" to tournamentName
                    ),
                    tournament.additionalData
                )
            }
        )
    }

    @Test
    fun makeTournamentShouldThrowException_WhenWrongStartDateFormatProvidedForDataSource1() {
        // given
        val startDate = "09-07-21"
        val tournamentData1Payload = createDataSource1PayloadString(startDate, endDate, roundCount)

        // then
        assertThrows<ParseException> {
            tournamentAdapter.makeTournament(dataSource1, tournamentData1Payload)
        }
    }

    @Test
    fun makeTournamentShouldThrowException_WhenWrongEndDateFormatProvidedForDataSource1() {
        // given
        val endDate = "09-07-21"
        val tournamentData1Payload = createDataSource1PayloadString(startDate, endDate, roundCount)

        // then
        assertThrows<ParseException> {
            tournamentAdapter.makeTournament(dataSource1, tournamentData1Payload)
        }
    }

    @Test
    fun makeTournamentShouldThrowException_WhenWrongRoundCountTypeProvidedForDataSource1() {
        // given
        val roundCount = "roundCount"
        val tournamentData1Payload = createDataSource1PayloadString(startDate, endDate, roundCount)

        // then
        assertThrows<InvalidFormatException> {
            tournamentAdapter.makeTournament(dataSource1, tournamentData1Payload)
        }
    }

    @Test
    fun makeTournamentCreatesTournamentFromDataSource2() {
        // given
        val tournamentData2Payload = createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)

        // when
        val tournament = tournamentAdapter.makeTournament(dataSource2, tournamentData2Payload)

        // then
        assertAll(
            "tournament",
            { assertEquals(tournamentUUID, tournament.externalId) },
            { assertEquals(epochStartInstant, tournament.tournamentStartDate) },
            { assertEquals(epochEndInstant, tournament.tournamentEndDate) },
            { assertEquals(golfCourse, tournament.golfCourseName) },
            { assertEquals(hostCountry, tournament.hostCountry) },
            { assertEquals(Integer.parseInt(rounds), tournament.numberOfRounds) },
            { assertEquals(dataSource2, tournament.tournamentDataSource) },
            {
                assertEquals(
                    mapOf(
                        "competitionName" to competitionName,
                        "playerCount" to playerCount
                    ),
                    tournament.additionalData
                )
            }
        )
    }

    @Test
    fun makeTournamentShouldThrowException_WhenWrongTournamentUUIDFormatProvidedForDataSource2() {
        // given
        val tournamentUUID = "1111"
        val tournamentData2Payload = createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)

        // then
        assertThrows<IllegalArgumentException> {
            tournamentAdapter.makeTournament(dataSource2, tournamentData2Payload)
        }
    }

    @Test
    fun makeTournamentShouldThrowException_WhenWrongEpochStartFormatProvidedForDataSource2() {
        // given
        val epochStart = "test"
        val tournamentData2Payload = createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)

        // then
        assertThrows<InvalidFormatException> {
            tournamentAdapter.makeTournament(dataSource2, tournamentData2Payload)
        }
    }

    @Test
    fun makeTournamentShouldThrowException_WhenWrongEpochFinishFormatProvidedForDataSource2() {
        // given
        val epochFinish = "test"
        val tournamentData2Payload = createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)

        // then
        assertThrows<InvalidFormatException> {
            tournamentAdapter.makeTournament(dataSource2, tournamentData2Payload)
        }
    }

    @Test
    fun makeTournamentShouldThrowException_WhenWrongRoundsFormatProvidedForDataSource2() {
        // given
        val rounds = "test"
        val tournamentData2Payload = createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)

        // then
        assertThrows<InvalidFormatException> {
            tournamentAdapter.makeTournament(dataSource2, tournamentData2Payload)
        }
    }
}
