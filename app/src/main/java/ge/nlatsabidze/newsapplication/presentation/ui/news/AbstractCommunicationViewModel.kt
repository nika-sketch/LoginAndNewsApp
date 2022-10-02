package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import ge.nlatsabidze.newsapplication.core.launchMain
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.core.Communication

abstract class AbstractCommunicationViewModel<S, E>(
    private val stateCommunication: Communication<S>,
    private val eventCommunication: Communication<E>,
    private val dispatchers: Dispatchers
) : ViewModel() {

    fun collectState(
        lifecycleOwner: LifecycleOwner,
        flowCollector: FlowCollector<S>
    ) = launchMain {
        stateCommunication.collect(lifecycleOwner, flowCollector)
    }

    fun collectEvent(
        lifecycleOwner: LifecycleOwner,
        flowCollector: FlowCollector<E>
    ) = launchMain {
        eventCommunication.collect(lifecycleOwner, flowCollector)
    }

    protected fun handle(
        block: suspend () -> Unit
    ) = dispatchers.launchBackground(viewModelScope) {
        block.invoke()
    }
}
