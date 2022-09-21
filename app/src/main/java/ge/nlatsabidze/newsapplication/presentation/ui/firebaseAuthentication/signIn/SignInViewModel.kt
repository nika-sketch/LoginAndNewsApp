package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.signIn

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.launchMain
import ge.nlatsabidze.newsapplication.domain.interactor.SignInInteractor
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseEvent
import ge.nlatsabidze.newsapplication.presentation.ui.news.AbstractCommunicationViewModel

@HiltViewModel
class SignInViewModel @Inject constructor(
    dispatcher: Dispatchers,
    private val signInCommunication: Communication<FirebaseEvent>,
    private val loadingCommunication: Communication<Visibility>,
    private val signInInteractor: SignInInteractor,
    private val firebaseEvent: FirebaseEvent
) : AbstractCommunicationViewModel<Visibility, FirebaseEvent>(
    loadingCommunication,
    signInCommunication,
    dispatcher
) {

    init {
        launchMain { signInCommunication.map(firebaseEvent) }
    }

    fun signIn(email: String, password: String) = handle {
        loadingCommunication.map(Visibility.Visible())
        signInInteractor.signIn(email, password).apply(signInCommunication)
        loadingCommunication.map(Visibility.Gone())
    }

    fun navigateToRegister() = launchMain {
        signInCommunication.map(
            FirebaseEvent.NavigateFromSignInToRegisterScreen()
        )
    }

}
