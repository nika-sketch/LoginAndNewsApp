package ge.nlatsabidze.newsapplication.core

import ge.nlatsabidze.newsapplication.data.model.ArticleMapper
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi

sealed class Result<T> {

    abstract fun <R> map(mapper: ResultMapper<R, T>): R

    class Loading<T> : Result<T>() {
        override fun <R> map(mapper: ResultMapper<R, T>): R {
            return mapper.mapProgress()
        }
    }

    data class Success<T>(private val data: T) : Result<T>() {
        override fun <R> map(mapper: ResultMapper<R, T>): R {
            return mapper.mapSuccess(data)
        }
    }

    data class Error<T>(private val message: String) : Result<T>() {
        override fun <R> map(mapper: ResultMapper<R, T>): R {
            return mapper.mapError(message)
        }
    }

}

interface ResultMapper<T, D> {

    fun mapProgress(): T
    fun mapError(message: String): T
    fun mapSuccess(data: D): T

    class ToNewsUi(private val mapper: ArticleMapper = ArticleMapper()) :
        ResultMapper<NewsUi, NewsDomain> {
        override fun mapProgress(): NewsUi = NewsUi.Loading()
        override fun mapError(message: String): NewsUi = NewsUi.Error(message)
        override fun mapSuccess(data: NewsDomain): NewsUi = NewsUi.Loading()
    }
}
