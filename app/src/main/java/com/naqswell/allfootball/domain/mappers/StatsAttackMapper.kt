package com.naqswell.allfootball.domain.mappers

import com.naqswell.allfootball.data.entities.StatsAttackResponse
import com.naqswell.allfootball.domain.entities.StatsModel

fun StatsAttackResponse.toDomain(): StatsModel.Attack {
    return StatsModel.Attack(
        team = team,
        tournament = tournament,
        shots1 = shots1,
        shots2 = shots2,
        dribbling = dribbling,
        foals = foals,
        rating = rating
    )
}

fun List<StatsAttackResponse>.toDomain(): List<StatsModel.Attack> {
    val statsModel = mutableListOf<StatsModel.Attack>()

    forEach { attackResponse ->
        statsModel.add(attackResponse.toDomain())
    }

    return statsModel
}