package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
    }

    override fun observes() {
        newsViewModel.collect {
            when (it) {
                is Resource.Loading -> binding.Loading.visible()
                is Resource.Success -> displayNews(it.data?.articles!!)
                is Resource.Error -> displayError(it.message!!)
            }
        }

        collectFlow(newsViewModel.navigation) {
            handleNavigation(it)
        }

    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    private fun displayNews(newsList: MutableList<Article>) {
        binding.Loading.gone()
        newsAdapter.setList(newsList)
    }

    private fun displayError(exception: String) {
        binding.NoConnection.text = exception
        binding.NoConnection.visible()
        binding.Loading.gone()
    }

    private fun initRecyclerView() = with(binding) {
        newsAdapter = NewsItemAdapter { article ->
            newsViewModel.navigateToDetailsPage(article)
        }
        News.adapter = newsAdapter
        News.layoutManager = LinearLayoutManager(requireContext())
    }

}