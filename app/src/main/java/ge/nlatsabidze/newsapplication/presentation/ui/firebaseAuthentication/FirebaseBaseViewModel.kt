package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication

import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.FlowCollector
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.core.Communication

abstract class FirebaseBaseViewModel(
    private val firebaseCommunication: Communication<FirebaseEvent>,
    private val loadingCommunication: Communication<Visibility>
) : ViewModel() {

    fun collectFirebaseAuth(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<FirebaseEvent>
    ) = viewModelScope.launch {
        firebaseCommunication.collect(viewLifecycleOwner, collector)
    }

    fun collectVisibility(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<Visibility>
    ) = viewModelScope.launch {
        loadingCommunication.collect(viewLifecycleOwner, collector)
    }
}