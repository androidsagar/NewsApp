package com.example.newsapp.koin

import com.example.newsapp.repos.NewsRepository
import com.example.newsapp.view_model.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsViewModel() }
}

val reposModule = module {
    single { NewsRepository() }
}