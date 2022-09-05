package ge.nlatsabidze.newsapplication.data.repository

import javax.inject.Inject
import ge.nlatsabidze.newsapplication.core.*
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.remote.NewsService
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain
import ge.nlatsabidze.newsapplication.data.cache.HandleService
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.domain.repository.NewsServiceRepository

// old version
class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsResponseMapper: Mapper<NewsResponse, NewsDomain>,
    private val baseResponseHandler: HandleResponse
) : NewsRepository {

    override suspend fun fetchNews(): Result<NewsDomain> =
        baseResponseHandler.handleResponse(newsResponseMapper) {
            newsService.fetchMarketItems()
        }
}

class NewsServiceRepositoryImpl @Inject constructor(
    private val handleService: HandleService
) : NewsServiceRepository {

    override suspend fun fetchNews(): NewsResult = handleService.handleService()
}


sealed class NewsResult {

    abstract fun handle(): NewsUi

    object LoadingResult : NewsResult() {
        override fun handle(): NewsUi = NewsUi.Loading()
    }

    class SuccessResult(private val items: MutableList<Article>) : NewsResult() {
        override fun handle(): NewsUi = NewsUi.Success(items)
    }

    class ErrorResult(private val errorMessage: String) : NewsResult() {
        override fun handle(): NewsUi = NewsUi.Error(errorMessage)
    }
}
