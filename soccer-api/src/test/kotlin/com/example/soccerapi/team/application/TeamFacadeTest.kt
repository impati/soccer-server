package com.example.soccerapi.team.application

import com.example.soccerapi.team.api.request.ScoutRequest
import com.example.soccerdomain.player.domain.*
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import com.example.soccerdomain.team.domain.TeamRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class TeamFacadeTest @Autowired constructor(
    val teamRepository: TeamRepository,
    val playerRepository: PlayerRepository,
    val teamFacade: TeamFacade
) {

    @Test
    fun scout() {
        val team = teamRepository.save(Team(name = "test1", league = League.LALIGA))
        val player = playerRepository.save(
            Player(
                name = "player1",
                position = setOf(Position.CAM, Position.CDM),
                stat = Stat(Basic(), Physical(), Pass(), Forward(), Defense(), GoalKeeping()),
                trait = setOf(Trait.HEADER),
                mainFoot = MainFoot.BOTH
            )
        )

        teamFacade.scout(team.id!!, ScoutRequest(listOf(player.id!!)))

        assertThat(team.player).hasSize(1)
        assertThat(player.team).isEqualTo(team)
    }
}

