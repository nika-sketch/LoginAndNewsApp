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

    class Base(uiBinding: NewsUi) : StateAbstract<NewsUi>(uiBinding)

    abstract class SharedAbstract<T> : Communication<T> {

        private val sharedFlow = MutableSharedFlow<T>()

        override suspend fun map(news: T) {
            sharedFlow.emit(news)
        }

        override suspend fun collect(collector: FlowCollector<T>) {
            sharedFlow.collect(collector)
        }
    }

    abstract class AbstractChannel<T>: Communication<T> {

        private val channel = Channel<T>()
        private val channelFlow = channel.receiveAsFlow()

        override suspend fun map(news: T) {
            channel.trySend(news)
        }

        override suspend fun collect(collector: FlowCollector<T>) {
            channelFlow.collect(collector)
        }
    }

}
