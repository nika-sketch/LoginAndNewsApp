package ge.nlatsabidze.newsapplication.data.repository

import javax.inject.Inject
import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.remote.NewsService
import ge.nlatsabidze.newsapplication.domain.interactor.MyNews
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.domain.repository.HandleResponse

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsResponseMapper: Mapper<NewsResponse, MyNews>,
    private val baseResponseHandler: HandleResponse
) : NewsRepository {

    override suspend fun fetchNews(): Result<MyNews> =
        baseResponseHandler.handleResponse(newsResponseMapper) {
            newsService.fetchMarketItems()
        }
}
