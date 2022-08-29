package ge.nlatsabidze.newsapplication.core

import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

interface HandleResponse {

    suspend fun <T, S> handleResponse(
        mapper: Mapper<T, S>,
        apiRequest: suspend () -> Response<T>
    ): Result<S>

    class Base @Inject constructor(
        private val handleResult: HandleResult,
        private val error: Error,
        private val handleException: HandleException
    ) : HandleResponse {

        override suspend fun <T, S> handleResponse(
            mapper: Mapper<T, S>,
            apiRequest: suspend () -> Response<T>
        ): Result<S> = try {
            val request = apiRequest.invoke()
            val body = request.body()
            handleResult.successCase(mapper.map(body!!))
        } catch (e: UnknownHostException) {
            handleResult.errorCase(error.message())
        } catch (e: Exception) {
            handleResult.errorCase(handleException.handle(e))
        }
    }
}