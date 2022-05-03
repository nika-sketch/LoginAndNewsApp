package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.common.collectFlow
import ge.nlatsabidze.newsapplication.common.gone
import ge.nlatsabidze.newsapplication.common.visible
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate) {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsItemAdapter

    override fun start() {
        initRecyclerView()
        onArticleItemClicked()
        observers()
    }

    private fun observers() {
        collectFlow(newsViewModel.uiState) {
            when (it) {
                is LatestNewsUiState.Loading -> binding.Loading.visible()
                is LatestNewsUiState.Success -> showNews(it.news)
                is LatestNewsUiState.Error -> showError(it.exception)
            }
        }
    }

    private fun showNews(newsList: MutableList<Article>) {
        binding.Loading.gone()
        newsAdapter.newsList = newsList
    }

    private fun showError(exception: String) {
        binding.NoConnection.text = exception
        binding.NoConnection.visible()
        binding.Loading.gone()
    }


    private fun initRecyclerView() {
        binding.apply {
            newsAdapter = NewsItemAdapter()
            News.adapter = newsAdapter
            News.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onArticleItemClicked() {
        newsAdapter.onArticleClicked = { article ->
            val actionToDetails =
                NewsFragmentDirections.actionNewsFragmentToDetailsFragment(article)
            findNavController().navigate(actionToDetails)
        }
    }

}