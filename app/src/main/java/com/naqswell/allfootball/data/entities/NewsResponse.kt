package com.naqswell.allfootball.data.entities

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @SerializedName("tittle") var tittle: String?,
    @SerializedName("text") var text: String?,
    @SerializedName("img") var img: String?,
    @SerializedName("date") var date: String?

)