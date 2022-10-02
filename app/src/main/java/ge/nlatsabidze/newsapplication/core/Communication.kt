package ge.nlatsabidze.newsapplication.core

import kotlinx.coroutines.launch
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.channels.Channel
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.MutableStateFlow
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import ge.nlatsabidze.newsapplication.presentation.ui.core.Navigation
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseEvent

interface Communication<T> {

    suspend fun map(data: T)
    suspend fun collect(lifecycleOwner: LifecycleOwner, collector: FlowCollector<T>)

    abstract class StateAbstract<T>(data: T) : Communication<T> {

        private val stateFlow = MutableStateFlow(data)

        override suspend fun map(data: T) {
            stateFlow.value = data
        }

        override suspend fun collect(
            lifecycleOwner: LifecycleOwner,
            collector: FlowCollector<T>
        ) {
            lifecycleOwner.lifecycleScope.launch {
                stateFlow.flowWithLifecycle(
                    lifecycleOwner.lifecycle,
                    Lifecycle.State.STARTED
                ).collect(collector)
            }
        }
    }

    class BaseNews(uiBinding: NewsUi) : StateAbstract<NewsUi>(uiBinding)
    class BaseLoading(visibility: Visibility) : StateAbstract<Visibility>(visibility)

    abstract class ChannelAbstract<T> : Communication<T> {

        private val channel = Channel<T>()

        override suspend fun map(data: T) {
            channel.send(data)
        }

        override suspend fun collect(
            lifecycleOwner: LifecycleOwner,
            collector: FlowCollector<T>
        ) {
            lifecycleOwner.lifecycleScope.launch {
                channel.receiveAsFlow().flowWithLifecycle(
                    lifecycleOwner.lifecycle,
                    Lifecycle.State.STARTED
                ).collect(collector)
            }
        }
    }

    class Channel : ChannelAbstract<Navigation>()
    class FirebaseAuthEvent : ChannelAbstract<FirebaseEvent>()
    class ObserveConnection : ChannelAbstract<Status>()
}
