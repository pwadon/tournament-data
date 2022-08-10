package com.tournament.data.golf.adapters.api.controllers

import com.tournament.data.golf.adapters.api.dto.dataSource.DataSource
import com.tournament.data.golf.adapters.api.dto.tournament.Tournament
import com.tournament.data.golf.adapters.api.service.TournamentApiService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tournament")
class GolfTournamentController(
    var tournamentApiService: TournamentApiService
) {

    @PostMapping("/create")
    fun createTournament(
        @RequestParam dataSource: DataSource,
        @RequestBody tournament: String
    ): ResponseEntity<Tournament> {
        return ResponseEntity<Tournament>(
            tournamentApiService.createTournament(dataSource, tournament),
            HttpStatus.CREATED
        )
    }
}
