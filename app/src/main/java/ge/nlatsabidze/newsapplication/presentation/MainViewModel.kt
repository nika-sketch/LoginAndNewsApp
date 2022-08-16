package ge.nlatsabidze.newsapplication.presentation

import android.os.Build
import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.launchMain
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.ObserveConnectivity
import ge.nlatsabidze.newsapplication.core.ProvideResources

@RequiresApi(Build.VERSION_CODES.N)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val observeConnectivity: ObserveConnectivity,
    private val observeCommunicationChannel: Communication<ObserveConnectivity.Status>,
    private val resources: ProvideResources
) : ViewModel() {

    init {
        launchMain {
            observeConnectivity.observe(resources).collectLatest {
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
