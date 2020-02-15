package com.example.newsapp.koin

import com.example.newsapp.BuildConfig
import com.example.newsapp.retrofit.NewsRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val retrofitModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}

val networkRequestModule = module {
    single { provideNewsRequest(get()) }
}

//private fun provideGsonFactory(): GsonConverterFactory {
//    val gsonBuilder = GsonBuilder()
//    gsonBuilder.registerTypeAdapter(NewsResponse::class.java, ConverterFactory())
//    return GsonConverterFactory.create(gsonBuilder.create())
//}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(2, TimeUnit.MINUTES)
        .build()
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder().client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URl)
        .build()
}

private fun provideNewsRequest(retrofit: Retrofit) = retrofit.create(NewsRequest::class.java)