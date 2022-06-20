package ge.nlatsabidze.newsapplication.common

interface Mapper<S, R> {
    fun map(source: S): R
}