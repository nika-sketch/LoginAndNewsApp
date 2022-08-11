package ge.nlatsabidze.newsapplication.presentation.ui.core

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.register.RegisterFragmentDirections
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsFragmentDirections
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.signIn.SignInFragmentDirections

interface Navigation {

    fun navigate(navController: NavController)

    abstract class AbstractDirection(
        private val navDirections: NavDirections
    ) : Navigation {
        override fun navigate(navController: NavController) {
            navController.navigate(navDirections)
        }
    }

    class NavigateToDetails(
        private val item: Article,
        navDirections: NavDirections = NewsFragmentDirections.actionNewsFragmentToDetailsFragment(item)
    ) : AbstractDirection(navDirections)

    class NavigateFromSignInToNews(
        navDirections: NavDirections = SignInFragmentDirections.actionSignInFragmentToNewsFragment()
    ) : AbstractDirection(navDirections)

    class NavigateFromRegisterToNews(
        navDirections: NavDirections = RegisterFragmentDirections.actionRegisterFragmentToNewsFragment()
    ) : AbstractDirection(navDirections)

    class NavigateFromSignInToRegisterScreen(
        navDirections: NavDirections = SignInFragmentDirections.actionSignInFragmentToRegisterFragment()
    ) : AbstractDirection(navDirections)
}
