package ge.nlatsabidze.newsapplication.presentation.ui.news

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.*
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.domain.interactor.NewsInteractor
import ge.nlatsabidze.newsapplication.presentation.ui.core.Navigation
import ge.nlatsabidze.newsapplication.presentation.ui.details.ArticleDetails

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val communicationNews: Communication<NewsUi>,
    private val channelCommunication: Communication<Navigation>,
    private val newsInteractor: NewsInteractor,
    dispatcher: Dispatchers,
    private val sharedDetails: ArticleDetails.Mutable
) : AbstractCommunicationViewModel<NewsUi, Navigation>(
    communicationNews,
    channelCommunication,
    dispatcher
) {

    init {
        handle {
            newsInteractor.execute().collect {
                communicationNews.map(it.handle())
            }
        }
    }

    fun saveArticleAndNavigateToDetails(item: ArticleUi) = launchMain {
        channelCommunication.map(Navigation.NavigateToDetails())
        sharedDetails.save(item)
    }

    fun openNews(url: ArticleUi) = launchMain {
        channelCommunication.map(Navigation.NewsUrl(url))
    }

}
