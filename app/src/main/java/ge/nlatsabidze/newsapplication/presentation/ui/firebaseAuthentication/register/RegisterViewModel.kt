package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.register

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.domain.interactor.RegisterInteractor
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseEvent
import ge.nlatsabidze.newsapplication.presentation.ui.news.AbstractCommunicationViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    dispatchers: Dispatchers,
    private val registerCommunication: Communication<FirebaseEvent>,
    private val loadingCommunication: Communication<Visibility>,
    private val registerInteractor: RegisterInteractor
) : AbstractCommunicationViewModel<Visibility, FirebaseEvent>(
    loadingCommunication,
    registerCommunication,
    dispatchers
) {

    fun register(name: String, email: String, password: String) = handle {
        loadingCommunication.map(Visibility.Visible())
        registerInteractor.register(name, email, password).apply(registerCommunication)
        loadingCommunication.map(Visibility.Gone())
    }
}