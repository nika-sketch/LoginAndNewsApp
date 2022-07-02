package ge.nlatsabidze.newsapplication.common

interface HandleException {

    fun handle(e: Exception): String

    class Base : HandleException {
        override fun handle(e: Exception): String = e.message!!
    }
}