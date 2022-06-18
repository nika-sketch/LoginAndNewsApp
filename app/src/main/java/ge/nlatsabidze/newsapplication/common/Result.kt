package ge.nlatsabidze.newsapplication.common

sealed class Result<out T> {
    class Loading<T> : Result<T>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val message: String) : Result<T>()
}