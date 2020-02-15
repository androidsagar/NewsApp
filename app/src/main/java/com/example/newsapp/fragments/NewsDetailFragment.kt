package com.example.newsapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.newsapp.data_models.NewsResponse
import com.example.newsapp.databinding.FragmentNewsDetailBinding
import com.example.newsapp.retrofit.ResponseWrapper
import com.example.newsapp.view_model.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class NewsDetailFragment : Fragment() {

    private val args by navArgs<NewsDetailFragmentArgs>()
    private val newsViewModel by sharedViewModel<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentNewsDetailBinding.inflate(inflater,container,false).apply {
            newsViewModel.getPopularNews().observe(viewLifecycleOwner, Observer {
               this.news = (it as ResponseWrapper.Success<NewsResponse>).data.newsList[args.position]
            })
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
