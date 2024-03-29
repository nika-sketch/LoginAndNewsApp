package ge.nlatsabidze.newsapplication.core

import android.os.Build
import android.net.Network
import android.content.Context
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import androidx.annotation.RequiresApi
import android.net.ConnectivityManager
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.distinctUntilChanged

@RequiresApi(Build.VERSION_CODES.N)
interface ObserveConnectivity {

    fun observe(provideResources: ProvideResources): Flow<Status>

    class Base(context: Context) : ObserveConnectivity {
        private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        override fun observe(provideResources: ProvideResources): Flow<Status> = callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(Status.Lost(provideResources)) }
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(Status.Available(provideResources)) }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
        }.distinctUntilChanged()
    }
}
