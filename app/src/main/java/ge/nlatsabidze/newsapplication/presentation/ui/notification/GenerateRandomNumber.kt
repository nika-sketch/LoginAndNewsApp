package ge.nlatsabidze.newsapplication.presentation.ui.notification

interface GenerateRandomNumber<T> {

    fun randomNumber(size: Int): T

    class Base : GenerateRandomNumber<Int> {
        override fun randomNumber(size: Int): Int = (0..size).random()
    }
}