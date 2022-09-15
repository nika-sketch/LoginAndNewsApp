package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate), OnItemClick<ArticleUi> {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        newsAdapter = NewsItemAdapter(this@NewsFragment)
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())

        viewModel.collectNews(viewLifecycleOwner) { it.apply(binding, newsAdapter) }
        viewModel.collectNavigation(viewLifecycleOwner) { it.apply(findNavController(), this@NewsFragment) }
    }

    override fun onItemClick(item: ArticleUi) { viewModel.saveArticleAndNavigateToDetails(item) }

    override fun onLongItemClick(url: ArticleUi) { viewModel.openNews(url) }
}
