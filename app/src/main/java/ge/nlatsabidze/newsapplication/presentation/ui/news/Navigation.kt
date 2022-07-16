package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import ge.nlatsabidze.newsapplication.data.model.Article


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
        navDirections: NavDirections = NewsFragmentDirections.actionNewsFragmentToDetailsFragment()
    ) : AbstractDirection(navDirections)
}
