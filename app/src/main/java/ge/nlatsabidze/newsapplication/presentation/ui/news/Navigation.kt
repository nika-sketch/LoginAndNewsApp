package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.presentation.ui.signIn.SignInFragmentDirections

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
        navDirections: NavDirections = NewsFragmentDirections.actionNewsFragmentToDetailsFragment(
            item
        )
    ) : AbstractDirection(navDirections)

    class NavigateToNews(
        navDirections: NavDirections = SignInFragmentDirections.actionSignInFragmentToNewsFragment()
    ) : AbstractDirection(navDirections)
}
