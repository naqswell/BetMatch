package com.naqswell.allfootball.domain.mappers

import com.naqswell.allfootball.data.entities.StatsDefenseResponse
import com.naqswell.allfootball.domain.entities.StatsModel

fun StatsDefenseResponse.toDomain(): StatsModel.Defense {
    return StatsModel.Defense(
        team = team,
        tournament = tournament,
        kicks = kicks,
        tackle = tackle,
        interception = interception,
        fouls = fouls,
        offsides = offsides,
        rating = rating
    )
}

fun List<StatsDefenseResponse>.toDomain(): List<StatsModel.Defense> {
    val statsModel = mutableListOf<StatsModel.Defense>()

    forEach { defenseResponse ->
        statsModel.add(defenseResponse.toDomain())
    }

    return statsModel
}