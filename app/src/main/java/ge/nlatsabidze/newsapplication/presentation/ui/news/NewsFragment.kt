package ge.nlatsabidze.newsapplication.presentation.ui.news

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

    override fun start() = with(binding) {
        newsAdapter = NewsItemAdapter { article ->
            findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToDetailsFragment(
                    article
                )
            )
        }
        News.adapter = newsAdapter
        News.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun observes() {
        newsViewModel.collect {
            it.apply(binding, newsAdapter)
        }
    }


}