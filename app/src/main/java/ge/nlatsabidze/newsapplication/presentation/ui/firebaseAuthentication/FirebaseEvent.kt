package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication

import android.view.View
import javax.inject.Inject
import androidx.fragment.app.Fragment
import ge.nlatsabidze.newsapplication.core.isEnabledAndClickable
import ge.nlatsabidze.newsapplication.core.showSnack
import ge.nlatsabidze.newsapplication.presentation.ui.core.Navigation

interface FirebaseEvent {

    fun apply(view: View, fragment: Fragment)

    abstract class Abstract(private val navigation: Navigation) : FirebaseEvent {
        override fun apply(view: View, fragment: Fragment) {
            navigation.apply(fragment)
        }
    }

    class Success : Abstract(Navigation.NavigateFromSignInToNews())
    class Register : Abstract(Navigation.NavigateFromRegisterToNews())
    class NavigateFromSignInToRegisterScreen :
        Abstract(Navigation.NavigateFromSignInToRegisterScreen())

    class Failure(private val message: String) : FirebaseEvent {
        override fun apply(view: View, fragment: Fragment) {
            view.showSnack(message)
            view.isEnabledAndClickable(true)
        }
    }

    class Notification @Inject constructor(
        private val provideWorker: StartPeriodicWorkRequest
    ) : FirebaseEvent {
        override fun apply(view: View, fragment: Fragment) = provideWorker.startWork()
    }
}
