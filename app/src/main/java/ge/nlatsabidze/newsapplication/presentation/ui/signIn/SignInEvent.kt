package ge.nlatsabidze.newsapplication.presentation.ui.signIn

import androidx.navigation.NavController
import ge.nlatsabidze.newsapplication.core.showSnack
import ge.nlatsabidze.newsapplication.databinding.SignInFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.core.Navigation

interface SignInEvent {

    fun apply(binding: SignInFragmentBinding, navController: NavController)

    class SuccessFulLogin(private val navigation: Navigation) : SignInEvent {
        override fun apply(binding: SignInFragmentBinding, navController: NavController) {
            navigation.navigate(navController)
        }
    }

    class ErrorLogin(private val message: String) : SignInEvent {
        override fun apply(binding: SignInFragmentBinding, navController: NavController) {
            binding.signInGlobal.showSnack(message)
        }
    }
}
