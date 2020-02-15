package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data_models.News
import com.example.newsapp.databinding.RowNewsListBinding

class NewsAdapter(private val newsClick: (Int) -> Unit) :RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private var list: List<News> = arrayListOf()

    fun updateFunction(newsList:List<News>){
        list = newsList
        notifyDataSetChanged()
    }

    inner class NewsHolder(val binding: RowNewsListBinding):RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                newsClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(RowNewsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.binding.news = list[position]
    }
}