package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication

import androidx.work.*
import android.view.View
import javax.inject.Inject
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import ge.nlatsabidze.newsapplication.core.isEnabledAndClickable
import ge.nlatsabidze.newsapplication.core.showSnack
import ge.nlatsabidze.newsapplication.presentation.ui.core.Navigation

interface FirebaseEvent {

    fun apply(view: View, navController: NavController, fragment: Fragment)

    abstract class Abstract(private val navigation: Navigation) : FirebaseEvent {
        override fun apply(view: View, navController: NavController, fragment: Fragment) {
            navigation.apply(navController, fragment)
        }
    }

    class Success : Abstract(Navigation.NavigateFromSignInToNews())
    class Register : Abstract(Navigation.NavigateFromRegisterToNews())
    class NavigateFromSignInToRegisterScreen :
        Abstract(Navigation.NavigateFromSignInToRegisterScreen())

    class Failure(private val message: String) : FirebaseEvent {
        override fun apply(view: View, navController: NavController, fragment: Fragment) {
            view.showSnack(message)
            view.isEnabledAndClickable(true)
        }
    }

    class Notification @Inject constructor(
        private val workRequest: PeriodicWorkRequest,
        private val existingPeriodicWorkPolicy: ExistingPeriodicWorkPolicy,
        private val workName: String
    ) : FirebaseEvent {
        override fun apply(view: View, navController: NavController, fragment: Fragment) {
            WorkManager.getInstance(view.context)
                .enqueue(workRequest)
        }
    }
}
