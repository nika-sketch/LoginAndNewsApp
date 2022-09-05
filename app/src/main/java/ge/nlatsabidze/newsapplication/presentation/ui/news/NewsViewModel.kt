package ge.nlatsabidze.newsapplication.presentation.ui.news

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.launchMain
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.domain.interactor.InteractorNews
import ge.nlatsabidze.newsapplication.presentation.ui.core.Navigation

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val communicationNews: Communication<NewsUi>,
    private val channelCommunication: Communication<Navigation>,
    private val interactorNews: InteractorNews,
    dispatcher: Dispatchers,
) : ViewModel() {

    init {
        dispatcher.launchBackground(viewModelScope) {
            interactorNews.execute().collect {
                communicationNews.map(it.handle())
            }
        }
    }

    fun navigateToDetails(item: Article) = launchMain {
        channelCommunication.map(Navigation.NavigateToDetails(item))
    }

    fun openNews(url: Article) = launchMain {
        channelCommunication.map(Navigation.NewsUrl(url))
    }

    fun collectNavigation(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<Navigation>
    ) = launchMain {
        channelCommunication.collect(viewLifecycleOwner, collector)
    }

    fun collectNews(viewLifecycleOwner: LifecycleOwner, collector: FlowCollector<NewsUi>) = launchMain {
        communicationNews.collect(viewLifecycleOwner, collector)
    }
}
