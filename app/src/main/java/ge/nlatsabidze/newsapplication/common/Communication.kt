package ge.nlatsabidze.newsapplication.common

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import ge.nlatsabidze.newsapplication.data.model.News

interface Communication {

    fun map(news: Resource<News>)
    suspend fun collect(collector: FlowCollector<Resource<News>>)

    class Base : Communication {

        private val newsLiveData = MutableStateFlow<Resource<News>>(Resource.EmptyData())

        override fun map(news: Resource<News>) {
            newsLiveData.value = news
        }

        override suspend fun collect(collector: FlowCollector<Resource<News>>) {
            newsLiveData.collect(collector)
        }

    }
}