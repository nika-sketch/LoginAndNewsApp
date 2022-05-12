package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate) {

    private val newsViewModel: NewsViewModel by viewModel()
    private lateinit var newsAdapter: NewsItemAdapter

    override fun start() {
        initRecyclerView()
        onArticleItemClicked()
    }

    override fun observes() {
        newsViewModel.collect {
            when (it) {
                is Resource.Loading -> displayLoading()
                is Resource.Success -> displayNews(it.data?.articles!!)
                is Resource.Error -> displayError(it.message!!)
            }
        }
    }

    private fun displayLoading() = binding.Loading.visible()

    private fun displayNews(newsList: MutableList<Article>) {
        binding.Loading.gone()
        newsAdapter.newsList = newsList
    }

    private fun displayError(exception: String) {
        binding.NoConnection.text = exception
        binding.NoConnection.visible()
        binding.Loading.gone()
    }


    private fun initRecyclerView() = with(binding) {
        newsAdapter = NewsItemAdapter()
        News.adapter = newsAdapter
        News.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun onArticleItemClicked() {
        newsAdapter.onArticleClicked = { article ->
            val actionToDetails =
                NewsFragmentDirections.actionNewsFragmentToDetailsFragment(article)
            findNavController().navigate(actionToDetails)
        }
    }

}