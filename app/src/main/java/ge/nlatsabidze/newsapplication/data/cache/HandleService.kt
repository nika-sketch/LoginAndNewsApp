package ge.nlatsabidze.newsapplication.data.cache

import ge.nlatsabidze.newsapplication.core.Error
import ge.nlatsabidze.newsapplication.core.HandleException
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.model.NewsResponseToNewDomain
import ge.nlatsabidze.newsapplication.data.remote.NewsService
import ge.nlatsabidze.newsapplication.data.repository.NewsResult
import ge.nlatsabidze.newsapplication.domain.cache.ArticleRepository
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain
import java.net.UnknownHostException
import javax.inject.Inject

interface HandleService {

    suspend fun handleService(): NewsResult

    class Base @Inject constructor(
        private val error: Error,
        private val handleException: HandleException,
        private val handleArticles: HandleArticles,
        private val handleService: HandleService,
    ) : HandleService {
        override suspend fun handleService(): NewsResult = try {
            handleService.handleService()
        } catch (e: UnknownHostException) {
            handleArticles.handleArticleCache(error.message())
        } catch (e: Exception) {
            handleArticles.handleArticleCache(handleException.handle(e))
        }
    }
}

class SuccessResult @Inject constructor(
    private val newsService: NewsService,
    private val articleRepository: ArticleRepository,
    private val mapper: NewsResponse.Mapper<NewsDomain> = NewsResponseToNewDomain(),
) : HandleService {
    override suspend fun handleService(): NewsResult {
        val request = newsService.fetchMarketItems()
        val body = request.body()!!
        val newsDomain = body.map(mapper)
        articleRepository.deleteArticle()
        newsDomain.insertArticle(articleRepository)
        return newsDomain.successResult()
    }
}
