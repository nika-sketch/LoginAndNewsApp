package ge.nlatsabidze.newsapplication.data.repository

import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News
import javax.inject.Inject

interface NewsRepository  {

    suspend fun getNews(): Resource<News>
}