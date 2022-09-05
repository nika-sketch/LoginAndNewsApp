package ge.nlatsabidze.newsapplication.data.cache

import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.domain.cache.ArticleDao
import ge.nlatsabidze.newsapplication.domain.cache.ArticleRepository

class ArticleRepositoryImpl(private val articleDao: ArticleDao) : ArticleRepository {

    override fun deleteArticle() = articleDao.deleteArticle()
    override suspend fun insertArticle(listOfNews: List<Article>) = articleDao.insertArticle(listOfNews)
    override fun fetchArticle(): MutableList<Article> = articleDao.fetchArticle()
}