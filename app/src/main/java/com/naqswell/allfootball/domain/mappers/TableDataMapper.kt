package com.naqswell.allfootball.domain.mappers

import com.naqswell.allfootball.data.entities.TableDataResponse
import com.naqswell.allfootball.domain.entities.TableDataModel

fun TableDataResponse.toDomain(): TableDataModel {
    return TableDataModel(
        team = team,
        games = games,
        wons = wons,
        draws = draws,
        losses = losses,
        balls = balls,
        goals = goals,
        goalsPercent = goalsPercent
    )
}

fun List<TableDataResponse>.toDomain(): List<TableDataModel> {
    val tables = mutableListOf<TableDataModel>()

    forEach { defenseResponse ->
        tables.add(defenseResponse.toDomain())
    }

    return tables
}