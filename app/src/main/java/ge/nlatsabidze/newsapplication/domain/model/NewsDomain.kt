package ge.nlatsabidze.newsapplication.domain.model

import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.repository.NewsResult
import ge.nlatsabidze.newsapplication.domain.cache.ArticleRepository

data class NewsDomain(private val articles: MutableList<Article>) {

    suspend fun insertArticle(articleRepository: ArticleRepository) = articleRepository.insertArticle(articles)
    fun successResult(): NewsResult = NewsResult.SuccessResult(articles)
}
