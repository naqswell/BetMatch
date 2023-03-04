package com.naqswell.allfootball.data.entities

import com.google.gson.annotations.SerializedName

data class StatsAttackResponse(

    @SerializedName("Команда") var team: String,
    @SerializedName("Турнир") var tournament: String,
    @SerializedName("Удары з.и.") var shots1: Double,
    @SerializedName("Удары ВСтв з.и.") var shots2: Double,
    @SerializedName("Дриблинг з.и.") var dribbling: Double,
    @SerializedName("Дано фолов з.и.") var foals: Double,
    @SerializedName("Рейтинг") var rating: Double

)