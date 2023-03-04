package com.naqswell.allfootball.domain.entities

import com.naqswell.allfootball.domain.enums.Countries

data class TournamentTable(

    val type: Countries,
    val name: String,
    val dates: String,
    val category: String,
    val playersCount: Int,
    val icon: String,
    val data: List<TableDataModel>

)