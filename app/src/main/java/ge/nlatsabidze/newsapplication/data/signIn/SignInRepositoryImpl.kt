package ge.nlatsabidze.newsapplication.data.signIn

import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth
import ge.nlatsabidze.newsapplication.domain.signIn.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.signIn.UserAuthResult

class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SignInRepository {

    override suspend fun signIn(email: String, password: String): UserAuthResult =
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            UserAuthResult.SuccessAuth()
        } catch (e: Exception) {
            UserAuthResult.ExceptionAuth(e.message.toString())
        }
}
