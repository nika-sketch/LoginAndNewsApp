package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.lifecycle.LifecycleOwner
import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import ge.nlatsabidze.newsapplication.core.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val communicationNews: Communication<NewsUi>,
    private val channelCommunication: Communication<Navigation>,
    private val resultToNewsUiMapper: ResultToNewsUiMapper,
    dispatcher: Dispatchers,
) : ViewModel() {

    init {
        dispatcher.launchBackground(viewModelScope) {
            resultToNewsUiMapper.toNewsUi(newsUseCase.execute(), communicationNews)
        }
    }

    fun navigateToDetails(item: Article) = viewModelScope.launch {
        channelCommunication.map(Navigation.NavigateToDetails(item))
    }

    fun collectNavigation(
        viewLifecycleOwner: LifecycleOwner,
        collector: FlowCollector<Navigation>
    ) = viewModelScope.launch {
        channelCommunication.collect(viewLifecycleOwner, collector)
    }

    fun collectNews(viewLifecycleOwner: LifecycleOwner, collector: FlowCollector<NewsUi>) =
        viewModelScope.launch {
            communicationNews.collect(viewLifecycleOwner, collector)
        }
}
