package com.tournament.data.golf.adapters.api.service

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
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

    private var tournamentId = "174638"
    private var tournamentName = "Women's Open Championship"
    private var forecast = "fair"
    private var courseName = "Sunnydale Golf Course"
    private var countryCode = "GB"
    private var startDate = "09/07/21"
    private var startDateInstant = Instant.parse("2021-07-08T22:00:00Z")
    private var endDate = "13/07/21"
    private var endDateInstant = Instant.parse("2021-07-12T22:00:00Z")
    private var roundCount = "4"

    private var tournamentUUID = "87fc6650-e114-4179-9aef-6a9a13030f80"
    private var golfCourse = "Happy Days Golf Club"
    private var competitionName = "South West Invitational"
    private var hostCountry = "United States Of America"
    private var epochStart = "1638349200"
    private var epochStartInstant = Instant.ofEpochSecond(1638349200)
    private var epochFinish = "1638468000"
    private var epochEndInstant = Instant.ofEpochSecond(1638468000)
    private var rounds = "2"
    private var playerCount = "35"

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

    private fun createDataSource1PayloadString(startDate: String, endDate: String, roundCount: String): String {
        return "{\n" +
            "\t\"tournamentId\": \"$tournamentId\",\n" +
            "\t\"tournamentName\": \"$tournamentName\",\n" +
            "\t\"forecast\": \"$forecast\",\n" +
            "\t\"courseName\": \"$courseName\",\n" +
            "\t\"countryCode\": \"$countryCode\",\n" +
            "\t\"startDate\": \"$startDate\",\n" +
            "\t\"endDate\": \"$endDate\",\n" +
            "\t\"roundCount\": \"$roundCount\"\n" +
            "}"
    }

    private fun createDataSource2PayloadString(
        tournamentUUID: String,
        epochStart: String,
        epochFinish: String,
        rounds: String
    ): String {
        return "{\n" +
            "    \"tournamentUUID\":\"$tournamentUUID\",\n" +
            "    \"golfCourse\":\"$golfCourse\",\n" +
            "    \"competitionName\":\"$competitionName\",\n" +
            "    \"hostCountry\":\"$hostCountry\",\n" +
            "    \"epochStart\":\"$epochStart\",\n" +
            "    \"epochFinish\":\"$epochFinish\",\n" +
            "    \"rounds\":\"$rounds\",\n" +
            "    \"playerCount\":\"$playerCount\"\n" +
            "}"
    }
}
