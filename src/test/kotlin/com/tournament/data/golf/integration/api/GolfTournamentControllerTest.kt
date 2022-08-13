package com.tournament.data.golf.integration.api

import com.tournament.data.golf.adapters.api.dto.tournament.TournamentDto
import com.tournament.data.golf.utils.LocalPostgresContainer
import com.tournament.data.golf.utils.competitionName
import com.tournament.data.golf.utils.countryCode
import com.tournament.data.golf.utils.courseName
import com.tournament.data.golf.utils.createDataSource1PayloadString
import com.tournament.data.golf.utils.createDataSource2PayloadString
import com.tournament.data.golf.utils.dataSource1
import com.tournament.data.golf.utils.dataSource2
import com.tournament.data.golf.utils.endDate
import com.tournament.data.golf.utils.endDateInstant
import com.tournament.data.golf.utils.epochEndInstant
import com.tournament.data.golf.utils.epochFinish
import com.tournament.data.golf.utils.epochStart
import com.tournament.data.golf.utils.epochStartInstant
import com.tournament.data.golf.utils.forecast
import com.tournament.data.golf.utils.golfCourse
import com.tournament.data.golf.utils.hostCountry
import com.tournament.data.golf.utils.playerCount
import com.tournament.data.golf.utils.roundCount
import com.tournament.data.golf.utils.rounds
import com.tournament.data.golf.utils.startDate
import com.tournament.data.golf.utils.startDateInstant
import com.tournament.data.golf.utils.tournamentId
import com.tournament.data.golf.utils.tournamentName
import com.tournament.data.golf.utils.tournamentUUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.testcontainers.junit.jupiter.Testcontainers

@Import(LocalPostgresContainer::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
internal class GolfTournamentControllerTest {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate
    lateinit var postResponse: ResponseEntity<TournamentDto>
    var dataSource1RequestValue = "DATA_SOURCE_1"
    var dataSource2RequestValue = "DATA_SOURCE_2"

    @Test
    fun createTournamentShouldBeSuccessful_WhenProperPayloadForDataSource1Provided() {
        // when
        postResponse = testRestTemplate.postForEntity(
            createPostUrl(dataSource1RequestValue),
            createDataSource1PayloadString(startDate, endDate, roundCount)
        )

        // then
        assertAll(
            "response",
            { assertEquals(HttpStatus.CREATED.value(), postResponse.statusCodeValue) },
            { assertEquals(tournamentId, postResponse.body!!.externalId) },
            { assertEquals(startDateInstant, postResponse.body!!.tournamentStartDate) },
            { assertEquals(endDateInstant, postResponse.body!!.tournamentEndDate) },
            { assertEquals(courseName, postResponse.body!!.golfCourseName) },
            { assertEquals(countryCode, postResponse.body!!.hostCountry) },
            { assertEquals(Integer.parseInt(roundCount), postResponse.body!!.numberOfRounds) },
            { assertEquals(dataSource1, postResponse.body!!.tournamentDataSource) },
            {
                assertEquals(
                    mapOf(
                        "forecast" to forecast,
                        "tournamentName" to tournamentName
                    ),
                    postResponse.body!!.additionalData
                )
            }
        )
    }

    @Test
    fun createTournamentShouldThrowException_WhenWrongStartDateFormatProvidedForDataSource1() {
        // given
        val startDate = "09-07-21"

        // when
        val exception: ResponseEntity<RestClientException> = testRestTemplate.postForEntity(
            createPostUrl(dataSource1RequestValue),
            createDataSource1PayloadString(startDate, endDate, roundCount)
        )

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.statusCodeValue)
    }

    @Test
    fun createTournamentShouldThrowException_WhenWrongEndDateFormatProvidedForDataSource1() {
        // given
        val endDate = "09-07-21"

        // when
        val exception: ResponseEntity<RestClientException> = testRestTemplate.postForEntity(
            createPostUrl(dataSource1RequestValue),
            createDataSource1PayloadString(startDate, endDate, roundCount)
        )

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.statusCodeValue)
    }

    @Test
    fun createTournamentShouldThrowException_WhenWrongRoundCountFormatProvidedForDataSource1() {
        // given
        val roundCount = "roundCount"

        // when
        val exception: ResponseEntity<RestClientException> = testRestTemplate.postForEntity(
            createPostUrl(dataSource1RequestValue),
            createDataSource1PayloadString(startDate, endDate, roundCount)
        )

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.statusCodeValue)
    }

    @Test
    fun createTournamentShouldBeSuccessful_WhenProperPayloadForDataSource2Provided() {
        // when
        postResponse = testRestTemplate.postForEntity(
            createPostUrl(dataSource2RequestValue),
            createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)
        )

        // then
        assertAll(
            "response",
            { assertEquals(HttpStatus.CREATED.value(), postResponse.statusCodeValue) },
            { assertEquals(tournamentUUID, postResponse.body!!.externalId) },
            { assertEquals(epochStartInstant, postResponse.body!!.tournamentStartDate) },
            { assertEquals(epochEndInstant, postResponse.body!!.tournamentEndDate) },
            { assertEquals(golfCourse, postResponse.body!!.golfCourseName) },
            { assertEquals(hostCountry, postResponse.body!!.hostCountry) },
            { assertEquals(Integer.parseInt(rounds), postResponse.body!!.numberOfRounds) },
            { assertEquals(dataSource2, postResponse.body!!.tournamentDataSource) },
            {
                assertEquals(
                    mapOf(
                        "competitionName" to competitionName,
                        "playerCount" to playerCount
                    ),
                    postResponse.body!!.additionalData
                )
            }
        )
    }

    @Test
    fun createTournamentShouldThrowException_WhenWrongTournamentUUIDFormatProvidedForDataSource2() {
        // given
        val tournamentUUID = "1111"

        // when
        val exception: ResponseEntity<RestClientException> = testRestTemplate.postForEntity(
            createPostUrl(dataSource1RequestValue),
            createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)
        )

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.statusCodeValue)
    }

    @Test
    fun createTournamentShouldThrowException_WhenWrongEpochStartFormatProvidedForDataSource2() {
        // given
        val epochStart = "test"

        // when
        val exception: ResponseEntity<RestClientException> = testRestTemplate.postForEntity(
            createPostUrl(dataSource1RequestValue),
            createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)
        )

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.statusCodeValue)
    }

    @Test
    fun createTournamentShouldThrowException_WhenWrongEpochFinishFormatProvidedForDataSource2() {
        // given
        val epochFinish = "test"

        // when
        val exception: ResponseEntity<RestClientException> = testRestTemplate.postForEntity(
            createPostUrl(dataSource1RequestValue),
            createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)
        )

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.statusCodeValue)
    }

    @Test
    fun createTournamentShouldThrowException_WhenWrongRoundsFormatProvidedForDataSource2() {
        // given
        val rounds = "test"

        // when
        val exception: ResponseEntity<RestClientException> = testRestTemplate.postForEntity(
            createPostUrl(dataSource1RequestValue),
            createDataSource2PayloadString(tournamentUUID, epochStart, epochFinish, rounds)
        )

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.statusCodeValue)
    }
    fun createPostUrl(dataSource: String): String {
        return String.format("/tournament/create?dataSource=%s", dataSource)
    }
}
