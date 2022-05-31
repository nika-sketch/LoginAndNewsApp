package ge.nlatsabidze.newsapplication.domain.repository

import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News

interface NewsRepository  {

    suspend fun getNews(): Resource<News>
}