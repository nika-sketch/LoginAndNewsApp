package ge.nlatsabidze.newsapplication.presentation.ui.signIn

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.showSnack
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.domain.signIn.SignInRepository
import ge.nlatsabidze.newsapplication.databinding.SignInFragmentBinding

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository,
    private val dispatcher: Dispatchers,
    private val signInCommunication: Communication<EventSignIn>
) : ViewModel() {

    fun signIn(email: String, password: String) = dispatcher.launchBackground(viewModelScope) {
        val signIn = signInRepository.signIn(email, password)
        signIn.apply(signInCommunication)
    }

    fun collect(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<EventSignIn>
    ) = viewModelScope.launch {
        signInCommunication.collect(viewLifecycleOwner, collector)
    }
}

interface EventSignIn {

    fun apply(binding: SignInFragmentBinding)

    abstract class Abstract(private val message: String) : EventSignIn {
        override fun apply(binding: SignInFragmentBinding) {
            binding.signInGlobal.showSnack(message)
        }
    }

    class True : Abstract("Success")
    class False : Abstract("Error")
}