package ge.nlatsabidze.newsapplication.data.repository

import javax.inject.Inject
import ge.nlatsabidze.newsapplication.data.cache.HandleService
import ge.nlatsabidze.newsapplication.domain.repository.NewsServiceRepository

class BaseNewsServiceRepository @Inject constructor(
    private val handleService: HandleService
) : NewsServiceRepository {

    override suspend fun fetchNews(): NewsResult = handleService.handleService()
}