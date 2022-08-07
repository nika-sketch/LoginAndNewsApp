package ge.nlatsabidze.newsapplication.data.signIn

import ge.nlatsabidze.newsapplication.R
import ge.nlatsabidze.newsapplication.core.ProvideResources
import ge.nlatsabidze.newsapplication.domain.interactor.SignInInteractor
import ge.nlatsabidze.newsapplication.domain.signIn.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.signIn.UserAuthResult

class SignInInteractorImpl(
    private val repository: SignInRepository,
    private val provideResources: ProvideResources
) : SignInInteractor {
    override suspend fun signIn(email: String, password: String): UserAuthResult =
        if (email.isNotEmpty() && password.isNotEmpty()) {
            repository.signIn(email, password)
        } else {
            UserAuthResult.ErrorAuth(provideResources.string(R.string.input_error))
        }
}
