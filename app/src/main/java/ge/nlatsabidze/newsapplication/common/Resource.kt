package ge.nlatsabidze.newsapplication.common

sealed class Resource<T>(val data: T? = null, val message: String? = null, val empty: Int? = null) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class EmptyData<T>(emptyState: Int? = null) : Resource<T>(empty = emptyState)
}

sealed class Result<out T> {
    class Loading<T> : Result<T>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val message: String) : Result<T>()
}