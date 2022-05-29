package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow

class NewsViewModel(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: Communication<Resource<News>>,
    private val dispatcher: MyDispatchers,
    private val provideInternetConnectionChecker: ProvideInternetConnectionChecker
) : ViewModel() {

    private val _navigation = MutableSharedFlow<NavigationCommand>()
    val navigation: MutableSharedFlow<NavigationCommand> get() = _navigation

    init {
        getNews()
    }

    private fun getNews() = dispatcher.launchBackground(viewModelScope) {
        getNewsUseCase.invoke().collect { news ->
            communicationNews.map(news)
        }
    }

    fun collect(collector: FlowCollector<Resource<News>>) = vm {
        communicationNews.collect(collector)
    }

    private fun navigate(navDirections: NavDirections) = vm {
        _navigation.emit(NavigationCommand.ToDirection(navDirections))
    }

    fun navigateToDetailsPage(article: Article) {
        navigate(NewsFragmentDirections.actionNewsFragmentToDetailsFragment(article))
    }

}

sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
}



