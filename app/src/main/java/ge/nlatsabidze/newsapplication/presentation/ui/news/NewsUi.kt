package ge.nlatsabidze.newsapplication.presentation.ui.news

import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.core.visible
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter

interface NewsUi {

    fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter)

    abstract class Abstract(private val visibility: Visibility) : NewsUi {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) =
            visibility.apply(binding.loadingProgressBar)
    }

    class Loading : Abstract(Visibility.Visible())

    class Success(private var items: MutableList<Article>) : Abstract(Visibility.Gone()) {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) {
            super.apply(binding, adapter)
            adapter.submitList(items)
        }
    }

    class Error(private val message: String) : Abstract(Visibility.Gone()) {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) = with(binding) {
            super.apply(binding, adapter)
            errorMessageTextView.text = message
            errorMessageTextView.visible()
        }
    }
}
