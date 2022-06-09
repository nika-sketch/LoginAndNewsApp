package ge.nlatsabidze.newsapplication.presentation.ui.news

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import ge.nlatsabidze.newsapplication.common.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: StateCommunication<NewsUi>,
    dispatcher: MyDispatchers,
) : ViewModel() {

    init {
        dispatcher.launchBackground(viewModelScope) {
            getNewsUseCase.execute().collect { news ->
                val result = when (news) {
                    is Resource.Loading -> NewsUi.Loading()
                    is Resource.Success -> NewsUi.Success(news.data!!.articles)
                    is Resource.Error -> NewsUi.Error(news.message!!)
                    else -> throw IllegalStateException()
                }
                communicationNews.map(result)
            }
        }
    }

    fun collect(collector: FlowCollector<NewsUi>) = viewModelScope.launch {
        communicationNews.collect(collector)
    }
}










