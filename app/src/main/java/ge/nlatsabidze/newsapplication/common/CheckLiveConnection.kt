package ge.nlatsabidze.newsapplication.common

import javax.inject.Inject
import android.net.Network
import android.content.Context
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.content.Context.CONNECTIVITY_SERVICE
import dagger.hilt.android.qualifiers.ApplicationContext
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET

class CheckLiveConnection @Inject constructor(@ApplicationContext context: Context) :
    LiveData<Boolean>() {

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private val validNetworks: MutableSet<Network> = HashSet()

    private fun checkValidNetworks() {
        postValue(validNetworks.size > 0)
    }

    override fun onActive() {
        networkCallback = createNetworkCallback()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val checkInitialNet = cm.getNetworkCapabilities(cm.activeNetwork)
            ?.hasCapability(NET_CAPABILITY_INTERNET) == true

        if (!checkInitialNet) {
            postValue(false)
        }

        cm.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onInactive() {
        cm.unregisterNetworkCallback(networkCallback)
    }

    private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            val networkCapabilities = cm.getNetworkCapabilities(network)
            val hasInternetCapability = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
            if (hasInternetCapability == true) {
                validNetworks.add(network)
            }
            checkValidNetworks()
        }

        override fun onLost(network: Network) {
            validNetworks.remove(network)
            checkValidNetworks()
        }

    }

}