package ge.nlatsabidze.newsapplication.core

interface Mapper<S, R> {

    fun map(source: S): R
}