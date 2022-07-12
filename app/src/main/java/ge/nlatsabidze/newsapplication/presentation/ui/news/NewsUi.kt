package ge.nlatsabidze.newsapplication.presentation.ui.news

import ge.nlatsabidze.newsapplication.core.gone
import ge.nlatsabidze.newsapplication.core.visible
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter

interface NewsUi {

    fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter)

    class Loading : NewsUi {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) {
            binding.loadingProgressBar.visible()
        }
    }

    class Success(private var items: MutableList<Article>) : NewsUi {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) {
            binding.loadingProgressBar.gone()
            adapter.submitList(items)
        }
    }

    class Error(private val message: String) : NewsUi {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) = with(binding) {
            loadingProgressBar.gone()
            errorMessageTextView.text = message
            errorMessageTextView.visible()
        }
    }
}
