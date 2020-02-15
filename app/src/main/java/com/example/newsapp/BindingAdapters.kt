package com.example.newsapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.data_models.Media

@BindingAdapter("loadCircularImage")
fun loadCircularImage(view: ImageView, url:String){
    Glide.with(view.context).load(url).apply(RequestOptions.circleCropTransform()).into(view)
}
@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url:String){
    Glide.with(view.context).load(url).into(view)
}

