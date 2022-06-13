package ge.nlatsabidze.newsapplication.common

interface HandleResource {
    fun <T> successCase(data: T): Resource<T>
    fun <T> errorCase(text: String): Resource<T>

    class Base : HandleResource {
        override fun <T> successCase(data: T): Resource<T> = Resource.Success(data)
        override fun <T> errorCase(text: String): Resource<T> = Resource.Error(text)
    }
}