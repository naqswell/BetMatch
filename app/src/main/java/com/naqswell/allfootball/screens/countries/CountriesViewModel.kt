package com.naqswell.allfootball.screens.countries

import androidx.lifecycle.*
import com.naqswell.allfootball.data.remote.api.BetmatchApiService
import com.naqswell.allfootball.domain.entities.TournamentTable
import com.naqswell.allfootball.domain.enums.Countries
import com.naqswell.allfootball.domain.mappers.toDomain
import kotlinx.coroutines.launch

class CountriesViewModel constructor(private val apiService: BetmatchApiService) :
    ViewModel() {

    private val _tablesList = MutableLiveData<List<TournamentTable>>().apply {
        value = listOf()
    }
    val tablesList: LiveData<List<TournamentTable>> = _tablesList

    private val _onFetchFailed = MutableLiveData<String>()
    val onFetchFailed = _onFetchFailed

//    val table : LiveData<TableModel> = MediatorLiveData<TableModel>().apply {
//        addSource(_tablesList) { tableModels ->
//            if (tableModels.size > 0) {
//                value = tableModels[0]
//            }
//        }
//    }
    fun fetchTournamentTables() {
        viewModelScope.launch {
            apiService.getTournamentTables()
                .onSuccess { list ->
                    val tables = mutableListOf<TournamentTable>()
                    list.map { table ->
                        var countryType: Countries? = null
                        when {
                            Countries.Russia.value in table.name -> countryType = Countries.Russia
                            Countries.England.value in table.name -> countryType = Countries.England
                            Countries.Germany.value in table.name -> countryType = Countries.Germany
                            Countries.Spain.value in table.name -> countryType = Countries.Spain
                        }
                        countryType?.let { type -> tables.add(table.toDomain(type)) }
                    }
                    _tablesList.value = tables
                }
                .onFailure { _onFetchFailed.value = it.localizedMessage }
        }
    }

    companion object {
        fun provideFactory(betmatchApiService: BetmatchApiService): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CountriesViewModel(betmatchApiService) as T
                }
            }
    }
}