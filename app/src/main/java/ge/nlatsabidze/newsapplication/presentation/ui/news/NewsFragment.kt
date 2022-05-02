package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.common.gone
import ge.nlatsabidze.newsapplication.common.visible
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
    }

    override fun observes() = with(binding) {
        newsViewModel.observe(viewLifecycleOwner) { news ->
            when (news) {
                is Resource.Loading -> {
                    Loading.visible()
                }

                is Resource.Success -> {
                    Loading.gone()
                    newsAdapter.newsList = news.data?.articles!!
                }

                is Resource.Error -> {
                    NoConnection.text = news.message
                    NoConnection.visible()
                    Loading.gone()
                }
            }
        }
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