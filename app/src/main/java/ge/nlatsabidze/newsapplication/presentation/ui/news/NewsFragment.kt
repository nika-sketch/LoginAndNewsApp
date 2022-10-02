package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>(
    NewsFragmentBinding::inflate
), OnItemClick<ArticleUi> {

    private val viewModel by viewModels<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        val newsAdapter = NewsItemAdapter(this@NewsFragment)
        val newsHolder = NewsUiHolder.Base(binding, newsAdapter)
        viewModel.collectState(viewLifecycleOwner) { it.apply(newsHolder) }
        viewModel.collectEvent(viewLifecycleOwner) { it.apply(this@NewsFragment) }
    }

    override fun onItemClick(item: ArticleUi) { viewModel.saveArticleAndNavigateToDetails(item) }
    override fun onLongItemClick(url: ArticleUi) { viewModel.openNews(url) }
}
