package com.example.newsapp

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.newsapp.retrofit.ResponseWrapper

fun Context.showToast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun Context.showViews(vararg views: View){
    views.forEach { it.visibility = View.VISIBLE }
}

fun Context.hideViews(vararg views: View){
    views.forEach { it.visibility = View.GONE }
}

fun <T> LiveData<T>.isDataNull():Boolean?{
    return if(value == null || value is ResponseWrapper.Error)
        null
    else
        false
}