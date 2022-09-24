package ge.nlatsabidze.newsapplication.presentation.ui.core

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsFragmentDirections
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.signIn.SignInFragmentDirections
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.register.RegisterFragmentDirections

interface Navigation {

    fun apply(fragment: Fragment)

    abstract class AbstractDirection(
        private val navDirections: NavDirections
    ) : Navigation {
        override fun apply(fragment: Fragment) {
            fragment.findNavController().navigate(navDirections)
        }
    }

    class NavigateToDetails(
        navDirections: NavDirections = NewsFragmentDirections.actionNewsFragmentToDetailsFragment()
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

    class NewsUrl(
        private val article: ArticleUi,
        private val intent: String = Intent.ACTION_VIEW
    ) : Navigation {

        override fun apply(fragment: Fragment) {
            val intent = Intent(intent, article.parseUri())
            fragment.startActivity(intent)
        }
    }
}
