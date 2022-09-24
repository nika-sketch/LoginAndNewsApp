package ge.nlatsabidze.newsapplication.data.repository

import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.model.ArticleMapper
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi

sealed class NewsResult {

    abstract fun handle(): NewsUi

    object LoadingResult : NewsResult() {
        override fun handle(): NewsUi = NewsUi.Loading()
    }

    class SuccessResult(
        private val items: MutableList<Article>,
        private val mapper: Mapper<MutableList<Article>, MutableList<ArticleUi>> = ArticleMapper()
    ) : NewsResult() {
        override fun handle(): NewsUi = NewsUi.Success(mapper.map(items))
    }

    class ErrorResult(private val errorMessage: String) : NewsResult() {
        override fun handle(): NewsUi = NewsUi.Error(errorMessage)
    }
}