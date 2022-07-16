package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.Dispatchers
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val communicationNews: Communication<NewsUi>,
    private val communicationArticle: Communication<Article?>,
    private val channelCommunication: Communication<Navigation>,
    private val resultToNewsUiMapper: ResultToNewsUiMapper,
    dispatcher: Dispatchers,
) : ViewModel() {

    init {
        dispatcher.launchBackground(viewModelScope) {
            resultToNewsUiMapper.toNewsUi(newsUseCase.execute(), communicationNews)
        }
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

    fun collectArticle(viewLifecycleOwner: LifecycleOwner, collector: FlowCollector<Article?>) =
        viewModelScope.launch {
            communicationArticle.collect(viewLifecycleOwner, collector)
        }

    fun saveAndNavigateToDetails(item: Article) = viewModelScope.launch {
        channelCommunication.map(Navigation.NavigateToDetails())
        communicationArticle.map(item)
    }

}
