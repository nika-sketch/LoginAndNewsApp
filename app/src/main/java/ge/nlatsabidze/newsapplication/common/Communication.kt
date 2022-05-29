package ge.nlatsabidze.newsapplication.common

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

interface Communication<T> {

    fun map(news: T)
    suspend fun collect(collector: FlowCollector<T>)

    class Base<T>(data: T) : Communication<T> {

        private val stateFlow = MutableStateFlow(data)

        override fun map(news: T) {
            stateFlow.value = news
        }

        override suspend fun collect(collector: FlowCollector<T>) {
            stateFlow.collect(collector)
        }

    }
}