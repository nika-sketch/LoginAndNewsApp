package ge.nlatsabidze.newsapplication.data.repository

import ge.nlatsabidze.newsapplication.R
import ge.nlatsabidze.newsapplication.common.InternetConnection
import ge.nlatsabidze.newsapplication.common.Mapper
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.common.ResourceManager
import ge.nlatsabidze.newsapplication.data.model.MyNews
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.domain.repository.ResponseHandler
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val repository: NewsApi,
    private val newsResponseMapper: NewsResponseMapper,
    private val baseResponseHandler: ResponseHandler
) : NewsRepository {

    override suspend fun fetchNews(): Resource<MyNews> =
        baseResponseHandler.handleResponse(newsResponseMapper) {
            repository.fetchMarketItems()
        }
}

class NewsResponseMapper : Mapper<NewsResponse, MyNews> {
    override fun map(source: NewsResponse): MyNews {
        return MyNews(source.articles!!, source.status!!)
    }
}

