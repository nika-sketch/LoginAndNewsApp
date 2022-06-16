package ge.nlatsabidze.newsapplication.presentation.ui.news

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import ge.nlatsabidze.newsapplication.common.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: StateCommunication<NewsUi>,
    dispatcher: MyDispatchers,
) : ViewModel() {

    private val _newsUiState = MutableStateFlow(NewsUiStateInterface.NewsUiState())
    val newsUiState = _newsUiState.asStateFlow()

    init {
        dispatcher.launchBackground(viewModelScope) {
            getNewsUseCase.execute().collect { news ->
                when (news) {
                    is Resource.Loading -> _newsUiState.update { it.copy(loading = true) }
                    is Resource.Success -> _newsUiState.update { it.copy(newsList = news.data!!.articles) }
                    is Resource.Error -> _newsUiState.update { it.copy(errorMessage = news.message) }
                    else -> throw IllegalStateException()
                }
            }
        }
    }
}

interface NewsUiStateInterface {

    fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter)

    data class NewsUiState(
        private val loading: Boolean = false,
        private val newsList: MutableList<Article> = mutableListOf(),
        private val errorMessage: String? = null,
    ) : NewsUiStateInterface {
        override fun apply(binding: NewsFragmentBinding, adapter: NewsItemAdapter) {
            if (loading) {
                binding.loadingProgressBar.visible()
            }
            if (errorMessage?.isNotBlank() == true) {
                binding.loadingProgressBar.gone()
                binding.errorMessageTextView.showSnack(errorMessage)
            }

            if (newsList.isNotEmpty()) {
                binding.loadingProgressBar.gone()
                adapter.map(newsList)
            }
        }
    }
}











