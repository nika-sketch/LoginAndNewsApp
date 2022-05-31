package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: Communication<Resource<News>>,
    private val dispatcher: MyDispatchers,
) : ViewModel() {

    init {
        getNews()
    }

    private fun getNews() = dispatcher.launchBackground(viewModelScope) {
        getNewsUseCase.invoke().collect { news ->
            communicationNews.map(news)
        }
    }

    fun collect(collector: FlowCollector<Resource<News>>) = viewModelScope.launch {
        communicationNews.collect(collector)
    }
}




