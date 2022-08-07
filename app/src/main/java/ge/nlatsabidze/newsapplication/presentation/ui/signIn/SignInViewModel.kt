package ge.nlatsabidze.newsapplication.presentation.ui.signIn

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.domain.interactor.SignInInteractor
import ge.nlatsabidze.newsapplication.domain.signIn.SignInRepository
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val dispatcher: Dispatchers,
    private val signInCommunication: Communication<SignInEvent>,
    private val loadingCommunication: Communication<Visibility>,
    private val signInInteractor: SignInInteractor
) : ViewModel() {

    fun signIn(email: String, password: String) = dispatcher.launchBackground(viewModelScope) {
        loadingCommunication.map(Visibility.Visible())
        signInInteractor.signIn(email, password).apply(signInCommunication)
        loadingCommunication.map(Visibility.Gone())
    }

    fun collect(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<SignInEvent>
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
