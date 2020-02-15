package com.example.newsapp.repos

import com.example.newsapp.espresso.EspressoIdlingResource
import com.example.newsapp.retrofit.NewsRequest
import com.example.newsapp.retrofit.ResponseWrapper
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class NewsRepository:KoinComponent {

    private val newsRequest: NewsRequest by inject()

    suspend fun getPopularArticles() = flow {
        emit(ResponseWrapper.Loading)
        try{
            EspressoIdlingResource.increment()
            val res = newsRequest.getNewsList()
            emit(ResponseWrapper.Success(res))
        }catch (e:Exception){
            e.printStackTrace()
            emit(ResponseWrapper.Error(""))
        }
        EspressoIdlingResource.decrement()
    }

}