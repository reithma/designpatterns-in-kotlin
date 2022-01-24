package behavioural.strategy

interface Strategy {
    fun produceIntegerList(end: Int): List<Int>
}


class EvenStrategy : Strategy {
    override fun produceIntegerList(end: Int) = (0..end step 2).toList()
}

class OddStrategy : Strategy {
    override fun produceIntegerList(end: Int): List<Int> = (1..end step 2).toList()
}

class FibonacciStrategy : Strategy {
    override fun produceIntegerList(end: Int): List<Int> {
        val ret = mutableListOf<Int>()
        var t1 = 0
        var t2 = 1
        for (i in 0..end) {
            val sum = t1 + t2
            t1 = t2
            t2 = sum
            ret.add(sum)
        }
        return ret.toList()
    }
}

/**
 * The strategy-pattern is used when the behaviour or algorithm (logic) of an object can be changed at runtime.
 * This is achieved by wrapping the algorithm in a separate object which is used by the context-object
 */
class RangePrinter(private val strategy: Strategy, end: Int) {
    var end: Int = end
        set(value) {
            field = value
            recompute()
        }

    var rangeItems = strategy.produceIntegerList(end)

    private fun recompute() {
        rangeItems = strategy.produceIntegerList(end)
    }

    override fun toString(): String {
        return rangeItems.joinToString()
    }
}

class RangePrinterWithLambda(private val strategy: (Int) -> List<Int>, end: Int) {
    var end: Int = end
        set(value) {
            field = value
            recompute()
        }

    var rangeItems = strategy(end)

    private fun recompute() {
        rangeItems = strategy(end)
    }

    override fun toString(): String {
        return rangeItems.joinToString()
    }
}
