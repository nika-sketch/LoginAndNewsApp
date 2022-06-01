package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: Communication<NewsUi>,
    dispatcher: MyDispatchers,
) : ViewModel() {

    init {
        dispatcher.launchBackground(viewModelScope) {
            getNewsUseCase.invoke().collect { news ->
                val result = when (news) {
                    is Resource.Loading -> NewsUi.LoadingUi()
                    is Resource.Success -> NewsUi.SuccessUi(news.data?.articles!!)
                    is Resource.Error -> NewsUi.ErrorUi(news.message!!)
                    else -> throw IllegalStateException()
                }
                communicationNews.map(result)
            }
        }
    }

    fun collect(collector: FlowCollector<NewsUi>) = viewModelScope.launch {
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
            adapter.setList(items)
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


interface Mapper<S, R> {
    fun map(source: S): R
}








