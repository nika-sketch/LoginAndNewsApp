package ge.nlatsabidze.newsapplication.data.repository

import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News

interface NewsRepository  {

    suspend fun getNews(): Resource<News>
}