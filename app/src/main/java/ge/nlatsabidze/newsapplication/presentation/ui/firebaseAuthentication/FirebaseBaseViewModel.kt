package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.core.launchMain

abstract class FirebaseBaseViewModel(
    private val firebaseCommunication: Communication<FirebaseEvent>,
    private val loadingCommunication: Communication<Visibility>,
    private val dispatchers: Dispatchers
) : ViewModel() {

    fun collectFirebaseAuth(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<FirebaseEvent>
    ) = launchMain {
        firebaseCommunication.collect(viewLifecycleOwner, collector)
    }

    fun collectVisibility(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<Visibility>
    ) = launchMain {
        loadingCommunication.collect(viewLifecycleOwner, collector)
    }

    protected fun handle(
        block: suspend () -> Unit
    ) = dispatchers.launchBackground(viewModelScope) {
        block.invoke()
    }

}