package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.view.View
import javax.inject.Inject
import android.widget.TextView
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import ge.nlatsabidze.newsapplication.common.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: Communication<UiBinding>,
    dispatcher: MyDispatchers,
) : ViewModel() {

    init {
        dispatcher.launchBackground(viewModelScope) {
            getNewsUseCase.invoke().collect { news ->
                val result = when (news) {
                    is Resource.Loading -> UiBinding.Loading()
                    is Resource.Success -> UiBinding.Success(news.data?.articles!!)
                    is Resource.Error -> UiBinding.Error(news.message!!)
                    else -> throw IllegalStateException()
                }
                communicationNews.map(result)
            }
        }
    }

    fun collect(collector: FlowCollector<UiBinding>) = viewModelScope.launch {
        communicationNews.collect(collector)
    }
}


interface NewsUi {

    fun apply(progressBar: View, adapter: NewsItemAdapter, errorTextView: TextView)

    class LoadingUi : NewsUi {
        override fun apply(
            progressBar: View,
            adapter: NewsItemAdapter,
            errorTextView: TextView
        ) {
            progressBar.visible()
        }
    }

    class SuccessUi(private var items: MutableList<Article>) : NewsUi {
        override fun apply(
            progressBar: View,
            adapter: NewsItemAdapter,
            errorTextView: TextView
        ) {
            progressBar.gone()
            adapter.map(items)
        }
    }

    class ErrorUi(private val errorMessage: String) : NewsUi {
        override fun apply(
            progressBar: View,
            adapter: NewsItemAdapter,
            errorTextView: TextView
        ) {
            progressBar.gone()
            errorTextView.text = errorMessage
            errorTextView.visible()
        }

    }
}

interface UiBinding {

    fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter)

    class Loading : UiBinding {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) {
            binding.Loading.visible()
        }
    }

    class Success(private var items: MutableList<Article>): UiBinding {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) {
            binding.Loading.gone()
            adapter.map(items)
        }
    }

    class Error(private val message: String): UiBinding {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) {
            binding.Loading.gone()
            binding.NoConnection.text = message
            binding.NoConnection.visible()
        }
    }

}


interface Mapper<S, R> {
    fun map(source: S): R
}








