package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.register

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.domain.interactor.RegisterInteractor
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseBaseViewModel
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseEvent
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    dispatchers: Dispatchers,
    private val registerCommunication: Communication<FirebaseEvent>,
    private val loadingCommunication: Communication<Visibility>,
    private val registerInteractor: RegisterInteractor
) : FirebaseBaseViewModel(registerCommunication, loadingCommunication, dispatchers) {

    fun register(name: String, email: String, password: String) = handle {
        loadingCommunication.map(Visibility.Visible())
        registerInteractor.register(name, email, password).apply(registerCommunication)
        loadingCommunication.map(Visibility.Gone())
    }
}