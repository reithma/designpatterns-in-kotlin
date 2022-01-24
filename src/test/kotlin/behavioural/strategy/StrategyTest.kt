package behavioural.strategy

import org.assertj.core.api.Assertions
import org.junit.Test
import kotlin.test.assertEquals


val rangeLambda = { it: Int -> (0..it).toList() }
val reverseRange = { it: Int -> (it downTo 0).toList() }

class StrategyTest {

    @Test
    fun `even strategy test`() {
        val rangePrinter = RangePrinter(strategy = EvenStrategy(), end = 3)
        Assertions.assertThat(rangePrinter.rangeItems).isEqualTo(listOf(0, 2))
        rangePrinter.end = 7
        Assertions.assertThat(rangePrinter.rangeItems).isEqualTo(listOf(0, 2, 4, 6))
    }

    @Test
    fun `odd strategy test`() {
        val rangePrinter = RangePrinter(strategy = OddStrategy(), end = 3)
        Assertions.assertThat(rangePrinter.rangeItems).isEqualTo(listOf(1, 3))
        rangePrinter.end = 7
        Assertions.assertThat(rangePrinter.rangeItems).isEqualTo(listOf(1, 3, 5, 7))
    }

    @Test
    fun `fibonacci strategy test`() {
        val rangePrinter = RangePrinter(strategy = FibonacciStrategy(), end = 3)
        Assertions.assertThat(rangePrinter.rangeItems).isEqualTo(listOf(1, 2, 3, 5))
        rangePrinter.end = 10
        Assertions.assertThat(rangePrinter.rangeItems).isEqualTo(listOf(1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144))
    }

    @Test
    fun `anonymous strategy test`() {
        val rangePrinter = RangePrinterWithLambda(strategy = rangeLambda, end = 3)
        Assertions.assertThat(rangePrinter.rangeItems).isEqualTo(listOf(0, 1, 2, 3))
        rangePrinter.end = 10
        Assertions.assertThat(rangePrinter.rangeItems).isEqualTo(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    }

    @Test
    fun `anonymous strategy test with kotlin test lib`() {
        val rangePrinter = RangePrinterWithLambda(strategy = reverseRange, end = 3)
        assertEquals(rangePrinter.rangeItems, listOf(3, 2, 1, 0))
        rangePrinter.end = 10
        assertEquals(rangePrinter.rangeItems, listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0))
        assertEquals(rangePrinter.rangeItems, reverseRange(10))
    }


}
