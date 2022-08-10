package ge.nlatsabidze.newsapplication.data.firebaseAuthService

import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult
import javax.inject.Named

class SignInRepositoryImpl @Inject constructor(
    @Named("logIn") private val firebaseAuthentication: FirebaseAuthentication
) : SignInRepository {

    override suspend fun signIn(email: String, password: String): UserAuthResult =
        firebaseAuthentication.authenticateUser(email, password)
}
