package com.naqswell.allfootball.domain.entities

data class TableDataModel(

    val team: String,
    val games: Int,
    val wons: Int,
    val draws: Int,
    val losses: Int,
    val balls: String,
    val goals: Long,
    val goalsPercent: String

)