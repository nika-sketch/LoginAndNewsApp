package ge.nlatsabidze.newsapplication.common

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import ge.nlatsabidze.newsapplication.data.model.News
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

interface Communication<T> {

    fun map(news: T)
    suspend fun collect(collector: FlowCollector<T>)

    class Base<T>(data: T) : Communication<T> {

        private val topRatedLiveData = MutableStateFlow(data)

        override fun map(news: T) {
            topRatedLiveData.value = news
        }

        override suspend fun collect(collector: FlowCollector<T>) {
            topRatedLiveData.collect(collector)
        }

    }
}

interface ChannelFlow<T> {

    suspend fun map(data: T)
    suspend fun collect(collector: FlowCollector<T>)

    class Base<T>(data: T): ChannelFlow<T> {

        private val channel = Channel<T>()
        private val channelAsFlow = channel.receiveAsFlow()

        override suspend fun map(data: T) {
            channel.send(data)
        }

        override suspend fun collect(collector: FlowCollector<T>) {
            channelAsFlow.collect(collector)
        }

    }
}