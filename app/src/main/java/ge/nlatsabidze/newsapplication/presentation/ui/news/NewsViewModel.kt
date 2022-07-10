package ge.nlatsabidze.newsapplication.presentation.ui.news

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import ge.nlatsabidze.newsapplication.common.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val communicationNews: Communication<NewsUi>,
    private val resultFactory: ResultToNewsUiMapper,
    dispatcher: Dispatchers,
) : ViewModel() {

    private val _navigation = Channel<Navigation>()
    val navigation = _navigation.receiveAsFlow()

    init {
        dispatcher.launchBackground(viewModelScope) {

            resultFactory.toNewsUi(newsUseCase.execute(),communicationNews)
        }
    }

    fun navigateToDetails(item: Article) = viewModelScope.launch {
        _navigation.send(Navigation.NavigateToDetails(item))
    }

    fun collectNews(collector: FlowCollector<NewsUi>) = viewModelScope.launch {
        communicationNews.collect(collector)
    }
}
