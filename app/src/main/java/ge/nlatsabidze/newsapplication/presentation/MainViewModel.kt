package ge.nlatsabidze.newsapplication.presentation

import android.os.Build
import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.FlowCollector
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.ObserveConnectivity
import ge.nlatsabidze.newsapplication.core.launchMain

@RequiresApi(Build.VERSION_CODES.N)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val observeConnectivity: ObserveConnectivity,
    private val observeCommunicationChannel: Communication<ObserveConnectivity.Status>
) : ViewModel() {

    init {
        launchMain {
            observeConnectivity.observe().collectLatest {
                observeCommunicationChannel.map(it)
            }
        }
    }

    fun collectConnection(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<ObserveConnectivity.Status>
    ) = viewModelScope.launch {
        observeCommunicationChannel.collect(viewLifecycleOwner, collector)
    }
}