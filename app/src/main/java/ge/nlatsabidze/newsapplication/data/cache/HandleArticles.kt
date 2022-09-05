package ge.nlatsabidze.newsapplication.data.cache

import ge.nlatsabidze.newsapplication.data.repository.NewsResult
import ge.nlatsabidze.newsapplication.domain.cache.ArticleRepository
import javax.inject.Inject

interface HandleArticles {

    fun handleArticleCache(errorType: String): NewsResult

    class Base @Inject constructor(private val articleRepository: ArticleRepository) :
        HandleArticles {
        override fun handleArticleCache(errorType: String): NewsResult =
            if (articleRepository.checkIfArticlesExists()) {
                NewsResult.SuccessResult(articleRepository.fetchArticle())
            } else {
                NewsResult.ErrorResult(errorType)
            }
    }
}