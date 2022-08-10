package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication

import android.view.View
import androidx.navigation.NavController
import ge.nlatsabidze.newsapplication.core.showSnack
import ge.nlatsabidze.newsapplication.databinding.SignInFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.core.Navigation

interface SignInEvent {

    fun apply(view: View, navController: NavController)

    class Success(private val navigation: Navigation) : SignInEvent {
        override fun apply(view: View, navController: NavController) {
            navigation.navigate(navController)
        }
    }

    class Failure(private val message: String) : SignInEvent {
        override fun apply(view: View, navController: NavController) {
            view.showSnack(message)
        }
    }
}
