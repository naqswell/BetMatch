package com.naqswell.allfootball.domain.mappers

import com.naqswell.allfootball.data.entities.TournamentTableResponse
import com.naqswell.allfootball.domain.entities.TournamentTable
import com.naqswell.allfootball.domain.enums.Countries

fun TournamentTableResponse.toDomain(countryType: Countries): TournamentTable {

    return TournamentTable(
        type = countryType,
        name = name,
        dates = dates,
        category = category,
        playersCount = playersCount,
        icon = icon,
        data = data.toList().map {
            it.second.toDomain()
        }
    )
}

fun List<TournamentTableResponse>.toDomain(countryType: Countries): List<TournamentTable> {
    val tables = mutableListOf<TournamentTable>()

    forEach { defenseResponse ->
        tables.add(defenseResponse.toDomain(countryType))
    }

    return tables
}