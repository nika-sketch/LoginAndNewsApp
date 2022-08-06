package ge.nlatsabidze.newsapplication.presentation.ui.signIn

import androidx.navigation.NavController
import ge.nlatsabidze.newsapplication.core.showSnack
import ge.nlatsabidze.newsapplication.databinding.SignInFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.news.Navigation

interface EventSignIn {

    fun apply(binding: SignInFragmentBinding, navController: NavController)

    class SuccessFullLogin(private val navigation: Navigation) : EventSignIn {
        override fun apply(binding: SignInFragmentBinding, navController: NavController) {
            navigation.navigate(navController)
        }
    }

    class ErrorLogin(private val message: String) : EventSignIn {
        override fun apply(binding: SignInFragmentBinding, navController: NavController) {
            binding.signInGlobal.showSnack(message)
        }
    }
}