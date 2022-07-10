package ge.nlatsabidze.newsapplication.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import ge.nlatsabidze.newsapplication.presentation.ui.news.Navigation
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

interface Communication<T> {

    suspend fun map(data: T)
    suspend fun collect(viewLifecycleOwner: LifecycleOwner, collector: FlowCollector<T>)

    abstract class StateAbstract<T>(data: T) : Communication<T> {

        private val stateFlow = MutableStateFlow(data)

        override suspend fun map(data: T) {
            stateFlow.value = data
        }

        override suspend fun collect(
            viewLifecycleOwner: LifecycleOwner,
            collector: FlowCollector<T>
        ) {
            stateFlow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect(collector)
        }
    }

    class BaseNews(uiBinding: NewsUi) : StateAbstract<NewsUi>(uiBinding)

    abstract class ChannelAbstract<T> : Communication<T> {

        private val channel = Channel<T>()

        override suspend fun map(data: T) {
            channel.send(data)
        }

        override suspend fun collect(
            viewLifecycleOwner: LifecycleOwner,
            collector: FlowCollector<T>
        ) {
            channel.receiveAsFlow().flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect(collector)
        }
    }

    class BaseChannel : ChannelAbstract<Navigation>()
}
