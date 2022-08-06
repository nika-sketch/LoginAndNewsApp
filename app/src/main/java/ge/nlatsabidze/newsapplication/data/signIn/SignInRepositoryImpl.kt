package ge.nlatsabidze.newsapplication.data.signIn

import com.google.firebase.auth.FirebaseAuth
import ge.nlatsabidze.newsapplication.R
import ge.nlatsabidze.newsapplication.core.ProvideResources
import ge.nlatsabidze.newsapplication.domain.signIn.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.signIn.UserAuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val provideResources: ProvideResources
) : SignInRepository {

    override suspend fun signIn(email: String, password: String): UserAuthResult =
        if (email.isNotEmpty() && password.isNotEmpty()) {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
                UserAuthResult.SuccessAuth()
            } catch (e: Exception) {
                UserAuthResult.ExceptionAuth(e.message.toString())
            }
        } else UserAuthResult.ErrorAuth(provideResources.string(R.string.input_error))
}
