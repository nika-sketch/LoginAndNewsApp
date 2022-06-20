package ge.nlatsabidze.newsapplication.common

import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

interface Communication<T> {

    suspend fun map(news: T)
    suspend fun collect(collector: FlowCollector<T>)

    abstract class StateAbstract<T>(data: T) : Communication<T> {

        private val stateFlow = MutableStateFlow(data)

        override suspend fun map(news: T) {
            stateFlow.value = news
        }

        override suspend fun collect(collector: FlowCollector<T>) {
            stateFlow.collect(collector)
        }
    }

    class BaseNews(uiBinding: NewsUi) : StateAbstract<NewsUi>(uiBinding)
}
