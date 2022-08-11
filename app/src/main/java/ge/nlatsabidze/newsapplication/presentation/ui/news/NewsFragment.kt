package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate),
    OnItemClick<Article> {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        newsAdapter = NewsItemAdapter(this@NewsFragment)
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())

        newsViewModel.collectNews(viewLifecycleOwner) { it.apply(binding, newsAdapter) }
        newsViewModel.collectNavigation(viewLifecycleOwner) { it.navigate(findNavController()) }

    }

    override fun onItemClick(item: Article) {
        newsViewModel.navigateToDetails(item)
    }
}
