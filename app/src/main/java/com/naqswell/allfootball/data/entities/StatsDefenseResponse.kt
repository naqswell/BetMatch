package com.naqswell.allfootball.data.entities

import com.google.gson.annotations.SerializedName

data class StatsDefenseResponse(

    @SerializedName("Команда") var team: String,
    @SerializedName("Турнир") var tournament: String,
    @SerializedName("Удары з.и.") var kicks: Double,
    @SerializedName("Отборы з.и.") var tackle: Double,
    @SerializedName("Перехваты з.и.") var interception: Double,
    @SerializedName("Фолы з.и.") var fouls: Double,
    @SerializedName("Офсайды з.и.") var offsides: Double,
    @SerializedName("Рейтинг") var rating: Double

)