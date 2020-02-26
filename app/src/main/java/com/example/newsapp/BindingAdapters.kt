package com.example.newsapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.data_models.Media

@BindingAdapter("loadCircularImage")
fun loadCircularImage(view: ImageView, url:List<Media>){
    if(url.isNotEmpty() && url.first().mediaMetadata.isNotEmpty())
    Glide.with(view.context).load(url[0].mediaMetadata[0].url).apply(RequestOptions.circleCropTransform()).into(view)
}
@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url:List<Media>){
    if (url.isNotEmpty() && url.first().mediaMetadata.isNotEmpty())
    Glide.with(view.context).load(url[0].mediaMetadata[1].url).into(view)
}

