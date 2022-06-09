package ge.nlatsabidze.newsapplication.domain.repository

import ge.nlatsabidze.newsapplication.R
import ge.nlatsabidze.newsapplication.common.InternetConnection
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.common.ResourceManager
import retrofit2.Response
import javax.inject.Inject

//interface ResponseHandler {
//
//    suspend fun <T> handleResponse(apiRequest: suspend () -> Response<T>): Resource<T>
//
//    class Base @Inject constructor(
//        private val provideInternetConnectionChecker: InternetConnection,
//        private val resourceManager: ResourceManager
//    ) : ResponseHandler {
//        override suspend fun <T> handleResponse(apiRequest: suspend () -> Response<T>): Resource<T> {
//            if (provideInternetConnectionChecker.isNetworkConnected()) {
//                try {
//                    val response = apiRequest()
//                    val body = response.body()
//                    if (response.isSuccessful && body != null) {
//                        return Resource.Success(body)
//                    }
//                    return Resource.Error(response.errorBody().toString())
//
//                } catch (e: Exception) {
//                    return Resource.Error(e.printStackTrace().toString())
//                }
//            } else {
//                return Resource.Error(resourceManager.provide(R.string.no_internet_connection))
//            }
//        }
//
//    }
//}