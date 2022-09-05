package ge.nlatsabidze.newsapplication.presentation.ui.notification

interface ShuffleList<T> {

    fun shuffle(list: MutableList<T>)

    abstract class Abstract<T> : ShuffleList<T> {
        override fun shuffle(list: MutableList<T>) = list.shuffle()
    }

    class RandomShuffle : Abstract<GenerateContent>()
}
