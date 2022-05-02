package ge.nlatsabidze.newsapplication.data.repository

import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.domain.repository.ResponseHandler
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val repository: NewsApi,
    private val responseHandler: ResponseHandler
): NewsRepository {

    override suspend fun getNews(): Resource<News> {
        return responseHandler.handlerResponse {
            repository.getMarketItems()
        }
    }
}