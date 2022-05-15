package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.lifecycle.ViewModel
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.receiveAsFlow

class NewsViewModel(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: Communication<Resource<News>>,
    private val dispatcher: MyDispatchers,
    private val provideInternetConnectionChecker: ProvideInternetConnectionChecker
) : ViewModel() {

    private val _channel = Channel<Boolean>()
    val channel = _channel.receiveAsFlow()

    init {
        getNews()
        checkConnection()
    }

    private fun checkConnection() {
        vm {
            if (!provideInternetConnectionChecker.isNetworkConnected()) {
                _channel.send(false)
            }
        }
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


