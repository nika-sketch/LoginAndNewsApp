package ge.nlatsabidze.newsapplication.domain.repository

import ge.nlatsabidze.newsapplication.core.*
import retrofit2.Response
import javax.inject.Inject

interface HandleResponse {

    suspend fun <T, S> handleResponse(
        mapper: Mapper<T, S>,
        apiRequest: suspend () -> Response<T>
    ): Result<S>

    class Base @Inject constructor(
        private val internetConnection: InternetConnection,
        private val handleResult: HandleResult,
        private val error: Error,
        private val handleException: HandleException
    ) : HandleResponse {

        override suspend fun <T, S> handleResponse(
            mapper: Mapper<T, S>,
            apiRequest: suspend () -> Response<T>
        ): Result<S> {
            return if (internetConnection.isNetworkConnected()) {
                try {
                    val request = apiRequest.invoke()
                    val body = request.body()
                    if (request.isSuccessful && body != null) {
                        return handleResult.successCase(mapper.map(body))
                    }
                    handleResult.errorCase(request.errorBody().toString())
                } catch (e: Exception) {
                    handleResult.errorCase(handleException.handle(e))
                }
            } else {
                handleResult.errorCase(error.message())
            }
        }

    }
}