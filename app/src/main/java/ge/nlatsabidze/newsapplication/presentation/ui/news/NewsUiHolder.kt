package ge.nlatsabidze.newsapplication.presentation.ui.news

import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.core.visible
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter

sealed interface NewsUiHolder {

    fun applyLoading(visibility: Visibility)

    fun applySuccess(data: MutableList<ArticleUi>)

    fun applyError(message: String)

    class Base(
        private val binding: NewsFragmentBinding,
        private val newsAdapter: NewsItemAdapter
    ) : NewsUiHolder {

        override fun applyLoading(visibility: Visibility) =
            visibility.apply(binding.loadingProgressBar)

        override fun applySuccess(data: MutableList<ArticleUi>) {
            binding.rvNews.adapter = newsAdapter
            newsAdapter.submitList(data)
        }

        override fun applyError(message: String) = with(binding) {
            errorMessageTextView.text = message
            errorMessageTextView.visible()
        }
    }
}