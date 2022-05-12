package ge.nlatsabidze.newsapplication.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> Fragment.collectFlow(flow: Flow<T>, onCollect: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        ).collectLatest(onCollect)
    }
}

fun ViewModel.coroutineIO(dispatchers: MyDispatchers, firstBlock: suspend () -> Unit) =
    dispatchers.launchBackground(viewModelScope) {
        firstBlock.invoke()
    }

fun ViewModel.vm(block: suspend () -> Unit) = viewModelScope.launch {
    block.invoke()
}

fun Fragment.defaultScope(block: suspend () -> Unit) =
    viewLifecycleOwner.lifecycleScope.launch {
        block.invoke()
    }


