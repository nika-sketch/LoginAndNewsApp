package ge.nlatsabidze.newsapplication.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.N)
interface ObserveConnectivity {

    fun observe(): Flow<Status>

    sealed class Status {

        companion object {
            private const val NO_INTERNET_CONNECTION = "No Internet Connection"
        }

        abstract fun apply(view: View)

        object Lost : Status() {
            override fun apply(view: View) = view.showSnack(NO_INTERNET_CONNECTION)
        }
    }

    class Base(context: Context) : ObserveConnectivity {
        private val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        override fun observe(): Flow<Status> = callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(Status.Lost) }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
        }.distinctUntilChanged()
    }
}
