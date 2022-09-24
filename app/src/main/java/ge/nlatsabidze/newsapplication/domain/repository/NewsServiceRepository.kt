package ge.nlatsabidze.newsapplication.domain.repository
import ge.nlatsabidze.newsapplication.data.repository.NewsResult

interface NewsServiceRepository {

    suspend fun fetchNews(): NewsResult
}