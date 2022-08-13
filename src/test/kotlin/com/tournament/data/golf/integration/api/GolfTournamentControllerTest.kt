package com.tournament.data.golf.integration.api

import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.utils.LocalPostgresContainer
import com.tournament.data.golf.utils.createDataSource1PayloadString
import com.tournament.data.golf.utils.endDate
import com.tournament.data.golf.utils.roundCount
import com.tournament.data.golf.utils.startDate
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.context.annotation.Import
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.DirtiesContext
import org.testcontainers.junit.jupiter.Testcontainers

@DirtiesContext
@Suppress("UNCHECKED_CAST")
@Import(LocalPostgresContainer::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
internal class GolfTournamentControllerTest {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate
    lateinit var postResponse: ResponseEntity<Tournament>

    @Test
    fun createTournament() {
        val tournament: ResponseEntity<Tournament> = testRestTemplate.postForEntity(
            "/torunament/create",
            createDataSource1PayloadString(startDate, endDate, roundCount)
        )

        println(tournament)
    }
}
