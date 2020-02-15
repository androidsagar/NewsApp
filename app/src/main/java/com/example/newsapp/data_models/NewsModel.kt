package com.example.newsapp.data_models

import com.google.gson.annotations.SerializedName

data class NewsResponse(@SerializedName("results") val newsList:List<News>)

data class News(@SerializedName("byline") val author:String,
                val title:String,
                val abstract:String,
                val published_date:String,
                val media:List<Media>)

data class Media(@SerializedName("media-metadata") val mediaMetadata:List<MediaData>)

data class MediaData(val url:String)

