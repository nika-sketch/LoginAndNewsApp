package ge.nlatsabidze.newsapplication.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

interface MyDispatchers {

    fun launchBackground(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job
    suspend fun changeToUi(block: suspend CoroutineScope.() -> Unit)

    class Base : MyDispatchers {
        override fun launchBackground(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) = scope.launch(kotlinx.coroutines.Dispatchers.IO, block = block)

        override suspend fun changeToUi(block: suspend CoroutineScope.() -> Unit) {
            withContext(kotlinx.coroutines.Dispatchers.Main, block)
        }
    }
}
