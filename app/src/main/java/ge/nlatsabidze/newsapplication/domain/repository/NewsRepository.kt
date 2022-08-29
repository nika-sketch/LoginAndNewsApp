package ge.nlatsabidze.newsapplication.domain.repository

import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.data.repository.NewsResult
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain

/**
 * old version
 */
interface NewsRepository {

    suspend fun fetchNews(): Result<NewsDomain>
}


interface NewsServiceRepository {

    suspend fun fetchNews(): NewsResult
}