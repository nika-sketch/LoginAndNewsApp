package ge.nlatsabidze.newsapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test(expected = IllegalArgumentException::class)
    fun addition_isCorrect() {

        assertEquals(4_000_000_000, Sum().sum(2_000_000_000, 2_000_000_000))
    }

    @Test
    fun test() {
        assertEquals(4_000_000_000, Sum().sumLong(2_000_000_000, 2_000_000_000))
    }

    @Test
    fun testSumInts() {
        val sum = Sum()
        val actual = sum.sumInts(5, 6)
        assertEquals(true, sum.isInt)
        assertEquals(11, actual)
    }

    @Test
    fun testSumLongs() {
        val sum = Sum()
        val actual = sum.sumInts(2_000_000_000, 2_000_000_000)
        assertEquals(false, sum.isInt)
        assertEquals(4_000_000_000, actual)
    }
}
