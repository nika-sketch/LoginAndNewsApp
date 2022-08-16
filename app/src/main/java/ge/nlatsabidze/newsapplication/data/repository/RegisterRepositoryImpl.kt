package ge.nlatsabidze.newsapplication.data.repository

import ge.nlatsabidze.newsapplication.data.firebaseAuthService.FirebaseAuthentication
import javax.inject.Inject
import javax.inject.Named
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.RegisterRepository
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult

class RegisterRepositoryImpl @Inject constructor(
    @Named("register") private val firebaseAuthentication: FirebaseAuthentication
) : RegisterRepository {

    override suspend fun register(name: String, email: String, password: String): UserAuthResult =
        firebaseAuthentication.authenticateUser(email, password)
}

