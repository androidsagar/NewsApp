package com.example.newsapp

import android.app.Application
import com.example.newsapp.koin.networkRequestModule
import com.example.newsapp.koin.reposModule
import com.example.newsapp.koin.retrofitModule
import com.example.newsapp.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppController)
            modules(listOf(viewModelModule, retrofitModule, networkRequestModule, reposModule))
        }
    }
}