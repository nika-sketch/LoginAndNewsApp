package ge.nlatsabidze.newsapplication.domain.cache

import ge.nlatsabidze.newsapplication.data.model.Article

interface ArticleRepository {

    fun deleteArticle()

    suspend fun insertArticle(listOfNews: List<Article>)

    fun fetchArticle(): MutableList<Article>
}
