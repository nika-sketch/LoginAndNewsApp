package ge.nlatsabidze.newsapplication.core

interface HandleResult {
    fun <T> successCase(data: T): Result<T>
    fun <T> errorCase(data: String): Result<T>

    class Base : HandleResult {
        override fun <T> successCase(data: T): Result<T> {
            return Result.Success(data)
        }

        override fun <T> errorCase(data: String): Result<T> {
            return Result.Error(data)
        }
    }
}