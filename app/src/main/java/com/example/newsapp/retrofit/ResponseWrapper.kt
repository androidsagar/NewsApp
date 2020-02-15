package com.example.newsapp.retrofit

sealed class ResponseWrapper<out T> {
    object Loading : ResponseWrapper<Nothing>()
    data class Error(val msg: String) : ResponseWrapper<Nothing>()
    data class Success<T>(val data: T) : ResponseWrapper<T>()
}