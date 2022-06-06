package ge.nlatsabidze.newsapplication.common

import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

interface Communication<T> {

    fun map(news: T)
    suspend fun collect(collector: FlowCollector<T>)

    abstract class Abstract<T>(data: T) : Communication<T> {

        private val stateFlow = MutableStateFlow(data)

        override fun map(news: T) {
            stateFlow.value = news
        }

        override suspend fun collect(collector: FlowCollector<T>) {
            stateFlow.collect(collector)
        }

    }

    class Base(uiBinding: NewsUi): Abstract<NewsUi>(uiBinding)
}