package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication

import ge.nlatsabidze.newsapplication.core.Communication

sealed class UserAuthResult {

    abstract suspend fun apply(signInEvent: Communication<FirebaseEvent>)

    abstract class Abstract(private val eventSignIn: FirebaseEvent) : UserAuthResult() {
        override suspend fun apply(signInEvent: Communication<FirebaseEvent>) {
            signInEvent.map(eventSignIn)
        }
    }

    class ExceptionAuth(message: String) : Abstract(FirebaseEvent.Failure(message))
    class SuccessLoginAuth : Abstract(FirebaseEvent.Success())
    class SuccessRegisterAuth : Abstract(FirebaseEvent.Register())
    class ErrorAuth(message: String) : Abstract(FirebaseEvent.Failure(message))
}
