package ge.nlatsabidze.newsapplication.data.repository

import javax.inject.Inject
import ge.nlatsabidze.newsapplication.data.firebaseAuthService.FirebaseAuthentication
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult
import javax.inject.Named

class BaseSignInRepository @Inject constructor(
    @Named("logIn") private val firebaseAuthentication: FirebaseAuthentication
) : SignInRepository {

    override suspend fun signIn(email: String, password: String): UserAuthResult =
        firebaseAuthentication.authenticateUser(email, password)
}
