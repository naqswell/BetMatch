package com.naqswell.allfootball.data.entities

import com.google.gson.annotations.SerializedName

data class TournamentTableResponse(

    @SerializedName("name") val name: String,
    @SerializedName("dates") val dates: String,
    @SerializedName("category") val category: String,
    @SerializedName("players_count") val playersCount: Int,
    @SerializedName("icon") val icon: String,
    @SerializedName("data") val data: Map<String, TableDataResponse>

)