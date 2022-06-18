package ge.nlatsabidze.newsapplication.domain.repository

import ge.nlatsabidze.newsapplication.R
import ge.nlatsabidze.newsapplication.common.*
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

interface ResponseHandler {

    suspend fun <T, S> handleResponse(
        mapper: Mapper<T, S>,
        apiRequest: suspend () -> Response<T>
    ): Result<S>

    class Base @Inject constructor(
        private val internetConnection: InternetConnection,
        private val resourceManager: ResourceManager,
        private val handleResource: HandleResult
    ) : ResponseHandler {

        override suspend fun <T, S> handleResponse(
            mapper: Mapper<T, S>,
            apiRequest: suspend () -> Response<T>
        ): Result<S> {
            return if (internetConnection.isNetworkConnected()) {
                try {
                    val request = apiRequest.invoke()
                    val body = request.body()
                    if (request.isSuccessful && body != null) {
                        return handleResource.successCase(mapper.map(body))
                    }
                    handleResource.errorCase(request.errorBody().toString())
                } catch (e: Exception) {
                    handleResource.errorCase(e.message.toString())
                }
            } else {
                handleResource.errorCase(resourceManager.provide(R.string.no_internet_connection))
            }
        }

    }
}