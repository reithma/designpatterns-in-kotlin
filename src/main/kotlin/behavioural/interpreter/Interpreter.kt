package behavioural.interpreter

interface Comparer {
    fun compare(condition: String): Boolean
}

class StringComparer(val data: String) : Comparer {
    override fun compare(condition: String): Boolean {
        return condition.contains(data)
    }

    override fun toString(): String {
        return "${this.hashCode()}: data=$data"
    }
}

class OrComparer(val exprOne: Comparer, val exprTwo: Comparer) : Comparer {
    override fun compare(condition: String): Boolean {
        return exprOne.compare(condition) || exprTwo.compare(condition)
    }
}

class AndComparer(val exprOne: Comparer, val exprTwo: Comparer) : Comparer {
    override fun compare(condition: String): Boolean {
        return exprOne.compare(condition) && exprTwo.compare(condition)
    }
}
