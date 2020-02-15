package com.example.newsapp.retrofit

import com.example.newsapp.BuildConfig
import com.example.newsapp.data_models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRequest {

    @GET("30.json")
    suspend fun getNewsList(@Query("api-key")apiKey:String = BuildConfig.NEWS_API_KEY):NewsResponse
}