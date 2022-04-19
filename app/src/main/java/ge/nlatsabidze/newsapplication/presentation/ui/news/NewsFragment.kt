package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.LinearLayoutManager
import ge.nlatsabidze.newsapplication.R
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate) {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsItemAdapter
    @Inject
    lateinit var liveConnection: CheckLiveConnection

    override fun start() {
        observeLiveConnection()
        initRecyclerView()
        onArticleItemClicked()
    }

    override fun observes() = collectFlow(newsViewModel.news) { news ->
        when (news) {
            is Resource.Loading -> {
                binding.Loading.visible()
            }
            is Resource.Success -> {
                binding.Loading.gone()
                newsAdapter.newsList = news.data?.articles as MutableList<Article>
            }
        }
    }

    private fun initRecyclerView() {
        newsAdapter = NewsItemAdapter()
        binding.News.adapter = newsAdapter
        binding.News.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onArticleItemClicked() {
        newsAdapter.onArticleClicked = { article ->
            val actionToDetails =
                NewsFragmentDirections.actionNewsFragmentToDetailsFragment(article)
            findNavController().navigate(actionToDetails)
        }
    }

    private fun observeLiveConnection() {
        collectFlow(liveConnection.asFlow()) { connection->
            if (!connection) {
                onSnack(binding.root, resources.getString(R.string.internetConnectionError), Color.RED)
            }
        }
    }
}