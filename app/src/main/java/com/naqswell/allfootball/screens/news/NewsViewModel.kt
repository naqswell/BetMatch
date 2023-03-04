package com.naqswell.allfootball.screens.news

import androidx.lifecycle.*
import com.naqswell.allfootball.data.remote.api.BetmatchApiService
import com.naqswell.allfootball.domain.entities.NewsModel
import com.naqswell.allfootball.domain.mappers.toDomain
import kotlinx.coroutines.launch

class NewsViewModel constructor(private val apiService: BetmatchApiService) :
    ViewModel() {

    private val _newsList = MutableLiveData<List<NewsModel>>().apply { value = listOf() }
    val newsList: LiveData<List<NewsModel>> = _newsList

    private val _onFetchFailed = MutableLiveData<String>()
    val onFetchFailed = _onFetchFailed

    fun fetchNews() {
        viewModelScope.launch {
            apiService.getNews()
                .onSuccess {
                    _newsList.value = it.toDomain()
                }
                .onFailure {}
        }
    }

    companion object {
        fun provideFactory(betmatchApiService: BetmatchApiService): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NewsViewModel(betmatchApiService) as T
                }
            }
    }
}