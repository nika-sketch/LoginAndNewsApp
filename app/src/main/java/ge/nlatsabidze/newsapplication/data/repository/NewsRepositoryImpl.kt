package ge.nlatsabidze.newsapplication.data.repository

import ge.nlatsabidze.newsapplication.R
import ge.nlatsabidze.newsapplication.common.InternetConnection
import ge.nlatsabidze.newsapplication.common.Mapper
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.common.ResourceManager
import ge.nlatsabidze.newsapplication.data.model.MyNews
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val repository: NewsApi,
    private val newsResponseMapper: NewsResponseMapper,
    private val baseResponseHandler: BaseResponseHandler
) : NewsRepository {

    override suspend fun getNews(): Resource<MyNews> =
        baseResponseHandler.handleResponse(newsResponseMapper) {
            repository.getMarketItems()
        }
}

class NewsResponseMapper : Mapper<NewsResponse, MyNews> {
    override fun map(source: NewsResponse): MyNews {
        return MyNews(source.articles!!, source.status!!)
    }
}

class BaseResponseHandler(
    private val internetConnection: InternetConnection,
    private val resourceManager: ResourceManager
) {
    suspend fun <T, S> handleResponse(
        mapper: Mapper<T, S>,
        apiRequest: suspend () -> Response<T>
    ): Resource<S> {
        if (internetConnection.isNetworkConnected()) {
            try {
                val request = apiRequest.invoke()
                val body = request.body()
                if (request.isSuccessful && body != null) {
                    return Resource.Success(mapper.map(body))
                }
                return Resource.Error(request.errorBody().toString())
            } catch (e: Exception) {
                return Resource.Error(e.message.toString())
            }
        } else {
            return Resource.Error(resourceManager.provide(R.string.no_internet_connection))
        }
    }
}
