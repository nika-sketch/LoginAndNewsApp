package ge.nlatsabidze.newsapplication.core

import android.os.Build
import android.view.View
import android.net.Network
import android.content.Context
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import androidx.annotation.RequiresApi
import android.net.ConnectivityManager
import ge.nlatsabidze.newsapplication.R
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.N)
interface ObserveConnectivity {

    fun observe(provideResources: ProvideResources): Flow<Status>

    sealed class Status {

        abstract fun apply(view: View)

        class Lost(private val provideResources: ProvideResources) : Status() {
            override fun apply(view: View) =
                view.showSnack(provideResources.string(R.string.no_internet_connection))
        }
    }

    class Base(context: Context) : ObserveConnectivity {
        private val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        override fun observe(provideResources: ProvideResources): Flow<Status> = callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(Status.Lost(provideResources)) }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
        }.distinctUntilChanged()
    }
}
