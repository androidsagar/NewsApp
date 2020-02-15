package com.example.newsapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data_models.NewsResponse
import com.example.newsapp.isDataNull
import com.example.newsapp.repos.NewsRepository
import com.example.newsapp.retrofit.ResponseWrapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsViewModel:ViewModel(),KoinComponent {

    private val newsRepository by inject<NewsRepository>()

    private val _newsList = MutableLiveData<ResponseWrapper<NewsResponse>>()
    private val newsList:LiveData<ResponseWrapper<NewsResponse>> = _newsList

    fun getPopularNews(): LiveData<ResponseWrapper<NewsResponse>> {
        newsList.isDataNull() ?: viewModelScope.launch {
            newsRepository.getPopularArticles().collect {
                _newsList.value = it
            }
        }
        return newsList
    }
}