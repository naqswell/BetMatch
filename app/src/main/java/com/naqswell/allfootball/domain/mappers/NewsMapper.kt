package com.naqswell.allfootball.domain.mappers

import com.naqswell.allfootball.data.entities.NewsResponse
import com.naqswell.allfootball.domain.entities.NewsModel

fun NewsResponse.toDomain(): NewsModel {
    return NewsModel(
        tittle = tittle,
        text = text,
        img = img,
        date = date
    )
}

fun List<NewsResponse>.toDomain(): List<NewsModel> {
    val newsModel = mutableListOf<NewsModel>()

    forEach { newsResponse ->
        newsModel.add(newsResponse.toDomain())
    }

    return newsModel
}