package ge.nlatsabidze.newsapplication.presentation.ui.signIn

import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.presentation.ui.core.Navigation

sealed class UserAuthResult {

    abstract suspend fun apply(signInEvent: Communication<SignInEvent>)

    abstract class Abstract(private val eventSignIn: SignInEvent) : UserAuthResult() {
        override suspend fun apply(signInEvent: Communication<SignInEvent>) {
            signInEvent.map(eventSignIn)
        }
    }

    class ExceptionAuth(message: String) : Abstract(SignInEvent.ErrorLogin(message))
    class SuccessAuth : Abstract(SignInEvent.SuccessFulLogin(Navigation.NavigateToNews()))
    class ErrorAuth(message: String) : Abstract(SignInEvent.ErrorLogin(message))
}
