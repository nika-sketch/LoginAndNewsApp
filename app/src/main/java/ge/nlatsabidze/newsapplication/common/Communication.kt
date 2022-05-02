package ge.nlatsabidze.newsapplication.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ge.nlatsabidze.newsapplication.data.model.News

interface Communication {

    fun map(news: Resource<News>)
    fun observeNews(owner: LifecycleOwner, observer: Observer<Resource<News>>)

    class Base : Communication {

        private val newsLiveData = MutableLiveData<Resource<News>>()

        override fun map(news: Resource<News>) {
            newsLiveData.postValue(news)
        }

        override fun observeNews(owner: LifecycleOwner, observer: Observer<Resource<News>>) {
            newsLiveData.observe(owner, observer)
        }

    }
}