package ge.nlatsabidze.newsapplication.data.interactor

import ge.nlatsabidze.newsapplication.R
import ge.nlatsabidze.newsapplication.core.ProvideResources
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.RegisterRepository
import ge.nlatsabidze.newsapplication.domain.interactor.RegisterInteractor
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult
import javax.inject.Inject

class BaseRegisterInteractor @Inject constructor(
    private val registerRepository: RegisterRepository,
    private val provideResources: ProvideResources
) : RegisterInteractor {

    override suspend fun register(name: String, email: String, password: String): UserAuthResult =
        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            registerRepository.register(name, email, password)
        } else {
            UserAuthResult.ErrorAuth(provideResources.string(R.string.input_error))
        }
}