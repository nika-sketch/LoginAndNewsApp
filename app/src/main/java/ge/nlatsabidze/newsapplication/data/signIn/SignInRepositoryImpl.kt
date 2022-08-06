package ge.nlatsabidze.newsapplication.data.signIn

import com.google.firebase.auth.FirebaseAuth
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.domain.signIn.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.signIn.EventSignIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SignInRepository {

    override suspend fun signIn(email: String, password: String): UserAuthResult =
        if (email.isNotEmpty() && password.isNotEmpty()) {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
                UserAuthResult.SuccessAuth()
            } catch (e: Exception) {
                UserAuthResult.ExceptionAuth()
            }
        } else {
            UserAuthResult.ErrorAuth()
        }
}

sealed class UserAuthResult {

    abstract suspend fun apply(signInEvent: Communication<EventSignIn>)

    abstract class Abstract(private val boolean: Boolean, private val eventSignIn: EventSignIn) :
        UserAuthResult() {
        override suspend fun apply(signInEvent: Communication<EventSignIn>) {
            signInEvent.map(eventSignIn)
        }
    }

    class ExceptionAuth : Abstract(false, EventSignIn.False())
    class SuccessAuth : Abstract(true, EventSignIn.True())
    class ErrorAuth : Abstract(false, EventSignIn.False())
}