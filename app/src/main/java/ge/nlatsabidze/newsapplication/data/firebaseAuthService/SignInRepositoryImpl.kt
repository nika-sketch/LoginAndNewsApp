package ge.nlatsabidze.newsapplication.data.firebaseAuthService

import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult

class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SignInRepository {

    override suspend fun signIn(email: String, password: String): UserAuthResult =
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            UserAuthResult.SuccessLoginAuth()
        } catch (e: Exception) {
            UserAuthResult.ExceptionAuth(e.message.toString())
        }
}
