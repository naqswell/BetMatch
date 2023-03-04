package com.naqswell.allfootball.screens.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naqswell.allfootball.MyApplication
import com.naqswell.allfootball.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModel.provideFactory(((requireActivity().application) as MyApplication).allSportsApi)
    }

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.apply {
            newsViewModel.fetchNews()

            newsAdapter = NewsAdapter { }
            recyclerView = rvNews.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = newsAdapter
            }

            newsViewModel.newsList.observe(viewLifecycleOwner) { newsModel ->
                newsAdapter.submitList(newsModel)
            }

        }.root
    }
}