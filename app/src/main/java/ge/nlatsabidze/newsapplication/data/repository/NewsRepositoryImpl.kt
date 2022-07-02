package ge.nlatsabidze.newsapplication.data.repository

import javax.inject.Inject
import ge.nlatsabidze.newsapplication.common.Mapper
import ge.nlatsabidze.newsapplication.common.Result
import ge.nlatsabidze.newsapplication.data.model.MyNews
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.domain.repository.HandleResponse

class NewsRepositoryImpl @Inject constructor(
    private val repository: NewsApi,
    private val newsResponseMapper: Mapper<NewsResponse, MyNews>,
    private val baseResponseHandler: HandleResponse
) : NewsRepository {

    override suspend fun fetchNews(): Result<MyNews> =
        baseResponseHandler.handleResponse(newsResponseMapper) {
            repository.fetchMarketItems()
        }
}

class NewsResponseMapper : Mapper<NewsResponse, MyNews> {
    override fun map(source: NewsResponse): MyNews {
        return MyNews(source.articles!!, source.status!!)
    }
}
