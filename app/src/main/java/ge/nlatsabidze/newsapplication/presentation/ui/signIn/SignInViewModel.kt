package ge.nlatsabidze.newsapplication.presentation.ui.signIn

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.FlowCollector
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.domain.signIn.SignInRepository

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository,
    private val dispatcher: Dispatchers,
    private val signInCommunication: Communication<EventSignIn>,
    private val loadingCommunication: Communication<Visibility>
) : ViewModel() {

    fun signIn(email: String, password: String) = dispatcher.launchBackground(viewModelScope) {
        loadingCommunication.map(Visibility.Visible())
        signInRepository.signIn(email, password).apply(signInCommunication)
        loadingCommunication.map(Visibility.Gone())
    }

    fun collect(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<EventSignIn>
    ) = viewModelScope.launch {
        signInCommunication.collect(viewLifecycleOwner, collector)
    }

    fun collectVisibility(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<Visibility>
    ) = viewModelScope.launch {
        loadingCommunication.collect(viewLifecycleOwner, collector)
    }
}
