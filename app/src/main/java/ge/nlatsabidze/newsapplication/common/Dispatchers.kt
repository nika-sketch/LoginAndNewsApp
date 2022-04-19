package ge.nlatsabidze.newsapplication.common

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope

interface Dispatchers {

    fun launchBackground(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job

    class Base : Dispatchers {
        override fun launchBackground(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) = scope.launch(kotlinx.coroutines.Dispatchers.IO, block = block)
    }
}