package ge.nlatsabidze.newsapplication.common

import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

interface StateCommunication<T> {

    fun map(news: T)
    suspend fun collect(collector: FlowCollector<T>)

    abstract class Abstract<T>(data: T) : StateCommunication<T> {

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



interface SharedCommunication<T> {

    suspend fun map(data: T)
    suspend fun collect(collector: FlowCollector<T>)

    abstract class Abstract<T> : SharedCommunication<T> {

        private val sharedFlow = MutableSharedFlow<T>()

        override suspend fun map(data: T) {
            sharedFlow.emit(data)
        }

        override suspend fun collect(collector: FlowCollector<T>) {
            sharedFlow.collect(collector)
        }
    }

    class Base: SharedCommunication.Abstract<NewsUi>()

}