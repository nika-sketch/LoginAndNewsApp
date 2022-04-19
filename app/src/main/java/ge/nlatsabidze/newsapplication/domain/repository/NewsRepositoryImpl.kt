package ge.nlatsabidze.newsapplication.domain.repository

import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.data.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val repository: NewsApi,
    private val responseHandler: ResponseHandler
): NewsRepository {

    override suspend fun getNews(): Resource<News> {
        return responseHandler.handlerResponse {
            repository.getMarketItems()
        }
    }

//    private suspend fun <T> handleResponse(apiCall: suspend () -> Response<T>): Resource<T> {
//        try {
//            val response = apiCall()
//            val body = response.body()
//            if (response.isSuccessful && body != null) {
//                return Resource.Success(body)
//            }
//            return Resource.Error(response.errorBody().toString())
//
//        } catch (e: Exception) {
//            return Resource.Error("exception")
//        }
//    }

}