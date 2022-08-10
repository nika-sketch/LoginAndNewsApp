package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication

import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.presentation.ui.core.Navigation

sealed class UserAuthResult {

    abstract suspend fun apply(signInEvent: Communication<SignInEvent>)

    abstract class Abstract(private val eventSignIn: SignInEvent) : UserAuthResult() {
        override suspend fun apply(signInEvent: Communication<SignInEvent>) {
            signInEvent.map(eventSignIn)
        }
    }

    class ExceptionAuth(message: String) : Abstract(SignInEvent.Failure(message))
    class SuccessSignInAuth : Abstract(SignInEvent.Success(Navigation.NavigateFromSignInToNews()))
    class SuccessRegisterAuth : Abstract(SignInEvent.Success(Navigation.NavigateFromRegisterToNews()))
    class ErrorAuth(message: String) : Abstract(SignInEvent.Failure(message))
}
