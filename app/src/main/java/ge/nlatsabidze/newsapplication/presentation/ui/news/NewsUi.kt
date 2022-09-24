package ge.nlatsabidze.newsapplication.presentation.ui.news

import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.data.model.ArticleUi

sealed interface NewsUi {

    fun apply(newsUiHolder: NewsUiHolder)

    abstract class Abstract(private val visibility: Visibility) : NewsUi {
        override fun apply(newsUiHolder: NewsUiHolder) = newsUiHolder.applyLoading(visibility)
    }

    class Loading : Abstract(Visibility.Visible())

    class Success(private val items: MutableList<ArticleUi>) : Abstract(Visibility.Gone()) {
        override fun apply(newsUiHolder: NewsUiHolder) {
            super.apply(newsUiHolder)
            newsUiHolder.applySuccess(items)
        }
    }

    class Error(private val message: String) : Abstract(Visibility.Gone()) {
        override fun apply(newsUiHolder: NewsUiHolder) {
            super.apply(newsUiHolder)
            newsUiHolder.applyError(message)
        }
    }
}
