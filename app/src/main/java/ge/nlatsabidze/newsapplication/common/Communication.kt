package ge.nlatsabidze.newsapplication.common

import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import kotlinx.coroutines.flow.*

interface Communication<T> {

    suspend fun map(data: T)
    suspend fun collect(collector: FlowCollector<T>)

    abstract class StateAbstract<T>(data: T) : Communication<T> {

        private val stateFlow = MutableStateFlow(data)

        override suspend fun map(data: T) {
            stateFlow.value = data
        }

        override suspend fun collect(collector: FlowCollector<T>) {
            stateFlow.collect(collector)
        }
    }

    class BaseNews(uiBinding: NewsUi) : StateAbstract<NewsUi>(uiBinding)
}
