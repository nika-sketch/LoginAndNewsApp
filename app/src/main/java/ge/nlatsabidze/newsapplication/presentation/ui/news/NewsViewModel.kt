package ge.nlatsabidze.newsapplication.presentation.ui.news

import javax.inject.Inject
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
    private val communicationNews: Communication<NewsUi>,
    dispatcher: MyDispatchers,
) : ViewModel() {

    init {
        dispatcher.launchBackground(viewModelScope) {
            getNewsUseCase.invoke().collect { news ->
                val result = when (news) {
                    is Resource.Loading -> NewsUi.Loading()
                    is Resource.Success -> NewsUi.Success(news.data?.articles!!)
                    is Resource.Error -> NewsUi.Error(news.message!!)
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










