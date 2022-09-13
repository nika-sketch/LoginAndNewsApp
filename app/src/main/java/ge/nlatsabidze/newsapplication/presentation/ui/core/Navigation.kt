package ge.nlatsabidze.newsapplication.presentation.ui.core

import android.net.Uri
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsFragmentDirections
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.signIn.SignInFragmentDirections
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.register.RegisterFragmentDirections

interface Navigation {

    fun apply(navController: NavController, fragment: Fragment)

    abstract class AbstractDirection(
        private val navDirections: NavDirections
    ) : Navigation {
        override fun apply(navController: NavController, fragment: Fragment) {
            navController.navigate(navDirections)
        }
    }

    class NavigateToDetails(
        private val article: ArticleUi,
        navDirections: NavDirections = NewsFragmentDirections.actionNewsFragmentToDetailsFragment(
            article
        )
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

        override fun apply(navController: NavController, fragment: Fragment) {
            val uri = Uri.parse(article.url)
            val intent = Intent(intent, uri)
            fragment.startActivity(intent)
        }
    }
}
