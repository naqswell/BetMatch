package com.naqswell.allfootball.data.remote.api

import com.naqswell.allfootball.Constants.BETMATCH_BASE_URL
import com.naqswell.allfootball.data.entities.NewsResponse
import com.naqswell.allfootball.data.entities.StatsAttackResponse
import com.naqswell.allfootball.data.entities.StatsDefenseResponse
import com.naqswell.allfootball.data.entities.TournamentTableResponse
import com.naqswell.allfootball.data.remote.exception.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BetmatchApiService {

    @GET("data_tournament_tables.json")
    suspend fun getTournamentTables(): Result<ArrayList<TournamentTableResponse>>

    @GET("data_statistic_defense.json")
    suspend fun getStatsDefense(): Result<ArrayList<StatsDefenseResponse>>

    @GET("data_statistic_attack.json")
    suspend fun getStatsAttack(): Result<ArrayList<StatsAttackResponse>>

    @GET("news.json")
    suspend fun getNews(): Result<ArrayList<NewsResponse>>

    companion object {
        private val loggingInterceptor =
            HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

        private val okHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BETMATCH_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ResultCallAdapterFactory())
                .build()
        }

        val apiService: BetmatchApiService by lazy {
            retrofit.create(BetmatchApiService::class.java)
        }
    }
}