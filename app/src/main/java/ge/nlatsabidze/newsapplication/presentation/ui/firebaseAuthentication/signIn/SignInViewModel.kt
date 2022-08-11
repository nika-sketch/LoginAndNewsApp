package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.signIn

import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.domain.interactor.SignInInteractor
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseEvent
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseBaseViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val dispatcher: Dispatchers,
    private val signInCommunication: Communication<FirebaseEvent>,
    private val loadingCommunication: Communication<Visibility>,
    private val signInInteractor: SignInInteractor
) : FirebaseBaseViewModel(signInCommunication, loadingCommunication) {

    fun signIn(email: String, password: String) = dispatcher.launchBackground(viewModelScope) {
        loadingCommunication.map(Visibility.Visible())
        signInInteractor.signIn(email, password).apply(signInCommunication)
        loadingCommunication.map(Visibility.Gone())
    }

    fun navigateToRegister() = viewModelScope.launch {
        signInCommunication.map(
            FirebaseEvent.NavigateFromSignInToRegisterScreen()
        )
    }

}
