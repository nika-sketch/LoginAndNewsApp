package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate) {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            newsAdapter = NewsItemAdapter { article ->
                findNavController().navigate(
                    NewsFragmentDirections.actionNewsFragmentToDetailsFragment(
                        article
                    )
                )
            }
            rvNews.adapter = newsAdapter
            rvNews.layoutManager = LinearLayoutManager(requireContext())
        }

        newsViewModel.collect {
            it.apply(binding, newsAdapter)
        }
    }
}