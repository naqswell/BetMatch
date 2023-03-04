package com.naqswell.allfootball.data.entities

import com.google.gson.annotations.SerializedName

data class TableDataResponse(

    @SerializedName("Команда") val team: String,
    @SerializedName("Игры") val games: Int,
    @SerializedName("В") val wons: Int,
    @SerializedName("Н") val draws: Int,
    @SerializedName("П") val losses: Int,
    @SerializedName("Мячи") val balls: String,
    @SerializedName("Очки") val goals: Long,
    @SerializedName("% очков") val goalsPercent: String

)
