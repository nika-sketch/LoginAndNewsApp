package ge.nlatsabidze.newsapplication.core

interface Delay {

    suspend fun delay()

    abstract class Abstract(private val time: Long) : Delay {
        override suspend fun delay() {
            kotlinx.coroutines.delay(time)
        }
    }

    class Base : Abstract(3000L)
}
