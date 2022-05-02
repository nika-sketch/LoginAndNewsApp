package ge.nlatsabidze.newsapplication.presentation.ui.news

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.common.Communication
import ge.nlatsabidze.newsapplication.common.Dispatchers
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: NewsUseCase,
    private val communicationNews: Communication,
    private val dispatchers: Dispatchers
) : ViewModel() {

    init {
        getNews()
    }

    private fun getNews() {
        dispatchers.launchBackground(viewModelScope) {
            getNewsUseCase.invoke().collect { news ->
                dispatchers.changeToUi {
                    communicationNews.map(news)
                }
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Resource<News>>) {
        communicationNews.observeNews(owner, observer)
    }
}
