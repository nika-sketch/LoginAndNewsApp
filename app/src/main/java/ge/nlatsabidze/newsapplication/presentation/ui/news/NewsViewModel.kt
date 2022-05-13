package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.lifecycle.ViewModel
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.flow.FlowCollector

class NewsViewModel(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: Communication,
    private val dispatcher: MyDispatchers
) : ViewModel() {

    init {
        getNews()
    }

    private fun getNews() {
        coroutineIO(dispatcher) {
            getNewsUseCase.invoke().collect { news ->
                communicationNews.map(news)
            }
        }
    }

    fun collect(collector: FlowCollector<Resource<News>>) = vm {
        communicationNews.collect(collector)
    }
}


