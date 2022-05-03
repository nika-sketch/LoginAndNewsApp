package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.common.Communication
import ge.nlatsabidze.newsapplication.common.Dispatchers
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: Communication,
    private val dispatchers: Dispatchers
) : ViewModel() {

    private val _uiState = MutableStateFlow<LatestNewsUiState>(LatestNewsUiState.Success(mutableListOf()))
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    init {
        getNews()
    }

    private fun getNews() {
        dispatchers.launchBackground(viewModelScope) {
            getNewsUseCase.invoke().collect { news ->
                when (news) {
                    is Resource.Loading -> {
                        _uiState.value = LatestNewsUiState.Loading
                    }
                    is Resource.Success -> {
                        _uiState.value = LatestNewsUiState.Success(news.data?.articles!!)
                    }
                    is Resource.Error -> {
                        _uiState.value = LatestNewsUiState.Error(news.message!!)
                    }
                }
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Resource<News>>) {
        communicationNews.observeNews(owner, observer)
    }
}

sealed class LatestNewsUiState {
    object Loading : LatestNewsUiState()
    data class Success(val news: MutableList<Article>): LatestNewsUiState()
    data class Error(val exception: String): LatestNewsUiState()
}