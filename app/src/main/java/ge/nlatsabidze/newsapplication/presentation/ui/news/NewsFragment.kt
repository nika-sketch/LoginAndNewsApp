package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate) {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsItemAdapter

    override fun start() {

        initRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            newsViewModel.news.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding.pbLoading.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.pbLoading.visibility = View.GONE
                        newsAdapter.newsList = it.data?.articles as MutableList<Article>
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        newsAdapter = NewsItemAdapter()
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
    }
}