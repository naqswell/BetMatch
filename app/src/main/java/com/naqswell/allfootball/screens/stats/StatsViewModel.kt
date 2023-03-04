package com.naqswell.allfootball.screens.stats

import android.util.Log
import androidx.lifecycle.*
import com.naqswell.allfootball.data.remote.api.BetmatchApiService
import com.naqswell.allfootball.domain.entities.StatsModel
import com.naqswell.allfootball.domain.mappers.toDomain
import kotlinx.coroutines.launch

class StatsViewModel constructor(private val apiService: BetmatchApiService) :
    ViewModel() {

    private val _defenseStatsListModel = MutableLiveData<List<StatsModel.Defense>>().apply {
        value = listOf()
    }
    val defenseStatsListModel: LiveData<List<StatsModel.Defense>> = _defenseStatsListModel

    private val _attackStatsListModel = MutableLiveData<List<StatsModel.Attack>>().apply {
        value = listOf()
    }
    val attackStatsListModel: LiveData<List<StatsModel.Attack>> = _attackStatsListModel

    private val _onFetchFailed = MutableLiveData<String>()
    val onFetchFailed = _onFetchFailed

    fun fetchDefenseStats() {
        viewModelScope.launch {
            apiService.getStatsDefense()
                .onSuccess {
                    Log.d("KEKK", it.toString())
                    _defenseStatsListModel.value = it.toDomain()
                }
                .onFailure {}
        }
    }

    fun fetchAttackStats() {
        viewModelScope.launch {
            apiService.getStatsAttack()
                .onSuccess {
                    Log.d("KEKK", it.toString())
                    _attackStatsListModel.value = it.toDomain()
                }
                .onFailure {}
        }
    }

    companion object {
        fun provideFactory(betmatchApiService: BetmatchApiService): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return StatsViewModel(betmatchApiService) as T
                }
            }
    }
}