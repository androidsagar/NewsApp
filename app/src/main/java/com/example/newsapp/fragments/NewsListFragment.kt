package com.example.newsapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.hideViews
import com.example.newsapp.retrofit.ResponseWrapper
import com.example.newsapp.showViews
import com.example.newsapp.view_model.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.get

/**
 * A simple [Fragment] subclass.
 */
class NewsListFragment : Fragment() {

    private val newsViewModel by sharedViewModel<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    private val newsClick:(Int)->Unit={
        activity?.findNavController(R.id.nav_host_fragment_main)?.navigate(NewsListFragmentDirections.actionNavNewsDetails(it))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerNews.run {
            adapter = NewsAdapter(newsClick)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        btn_retry.setOnClickListener {
            newsViewModel.getPopularNews()
        }

        newsViewModel.getPopularNews().observe(this, Observer {
            when (it) {
                is ResponseWrapper.Loading -> {
                    context?.showViews(progress)
                    context?.hideViews(group_error)
                }
                is ResponseWrapper.Success -> {
                    context?.hideViews(progress)
                    context?.showViews(recyclerNews)
                    (recyclerNews.adapter as NewsAdapter).updateFunction(it.data.newsList)
                }
                is ResponseWrapper.Error->{
                    context?.showViews(group_error)
                    context?.hideViews(progress)
                    label_error.text = it.msg
                }
            }
        })
    }
}

