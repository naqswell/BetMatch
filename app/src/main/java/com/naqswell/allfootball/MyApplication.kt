package com.naqswell.allfootball

import android.app.Application
import androidx.annotation.UiThread
import com.naqswell.allfootball.data.remote.api.BetmatchApiService
import com.naqswell.allfootball.domain.Prefs


@UiThread
class MyApplication : Application() {

    val allSportsApi: BetmatchApiService by lazy {
        BetmatchApiService.apiService
    }

    val prefs: Prefs by lazy {
        Prefs(applicationContext)
    }
}