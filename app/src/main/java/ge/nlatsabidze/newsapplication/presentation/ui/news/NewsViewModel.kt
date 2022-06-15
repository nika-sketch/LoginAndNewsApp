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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: StateCommunication<NewsUi>,
    dispatcher: MyDispatchers,
) : ViewModel() {

    private val _newsUiState = MutableStateFlow(NewsUiState())
    val newsUiState = _newsUiState.asStateFlow()

    init {
        dispatcher.launchBackground(viewModelScope) {
            getNewsUseCase.execute().collect { news ->
                when (news) {
                    is Resource.Loading -> _newsUiState.update {
                        it.copy(loading = true)
                    }
                    is Resource.Success -> _newsUiState.update {
                        it.copy(newsList = news.data!!.articles)
                    }
                    is Resource.Error -> _newsUiState.update {
                        it.copy(errorMessage = news.message)
                    }
                    else -> throw IllegalStateException()
                }
//                communicationNews.map(result)
            }
        }
    }

    fun collect(collector: FlowCollector<NewsUi>) = viewModelScope.launch {
        communicationNews.collect(collector)
    }
}

data class NewsUiState(
    val loading: Boolean = false,
    val newsList: MutableList<Article> = mutableListOf(),
    val errorMessage: String? = null
)










