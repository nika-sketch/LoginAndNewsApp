package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.util.Log.d
import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.usecases.GetNewsUseCase
import kotlinx.coroutines.flow.collectLatest

@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {

    private val _news = MutableStateFlow<Resource<News>>(Resource.EmptyData())
    val news: MutableStateFlow<Resource<News>> get() = _news

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            getNewsUseCase.invoke().collectLatest {
                _news.value = it
            }
        }
    }
}