package ge.nlatsabidze.newsapplication.presentation.ui.notification

import junit.framework.TestCase

class ShuffleListTest : TestCase() {

    fun testShuffling() {

        val shuffleList = RandomShuffle()

        val copy = mutableListOf(23, 341, 4, 43, 4, 1, 2, 4, 39, 123, 59, 23, 432, 53)
        val list = mutableListOf(23, 341, 4, 43, 4, 1, 2, 4, 39, 123, 59, 23, 432, 53)
        shuffleList.shuffle(list)

        assertTrue(copy != list)
    }

    private class RandomShuffle : ShuffleList.Abstract<Int>()
}