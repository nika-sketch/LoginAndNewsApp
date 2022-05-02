package ge.nlatsabidze.newsapplication.domain.repository

import ge.nlatsabidze.newsapplication.common.ProvideInternetConnectionChecker
import ge.nlatsabidze.newsapplication.common.Resource
import retrofit2.Response

interface ResponseHandler {

    suspend fun <T> handlerResponse(apicall: suspend() -> Response<T>): Resource<T>

    class Base(private val provideInternetConnectionChecker: ProvideInternetConnectionChecker): ResponseHandler {
        override suspend fun <T> handlerResponse(apicall: suspend () -> Response<T>): Resource<T> {
            if (provideInternetConnectionChecker.isNetworkConnected()) {
                try {
                    val response = apicall()
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        return Resource.Success(body)
                    }
                    return Resource.Error(response.errorBody().toString())

                } catch (e: Exception) {
                    return Resource.Error(e.printStackTrace().toString())
                }
            } else {
                return Resource.Error("No Internet Connection")
            }
        }

    }
}