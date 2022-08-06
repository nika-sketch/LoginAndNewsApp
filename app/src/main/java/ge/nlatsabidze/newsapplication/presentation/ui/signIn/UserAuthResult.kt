package ge.nlatsabidze.newsapplication.presentation.ui.signIn

import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.presentation.ui.news.Navigation

sealed class UserAuthResult {

    abstract suspend fun apply(signInEvent: Communication<EventSignIn>)

    abstract class Abstract(private val eventSignIn: EventSignIn) : UserAuthResult() {
        override suspend fun apply(signInEvent: Communication<EventSignIn>) {
            signInEvent.map(eventSignIn)
        }
    }

    class ExceptionAuth(message: String) : Abstract(EventSignIn.ErrorLogin(message))
    class SuccessAuth : Abstract(EventSignIn.SuccessFullLogin(Navigation.NavigateToNews()))
    class ErrorAuth(message: String) : Abstract(EventSignIn.ErrorLogin(message))
}