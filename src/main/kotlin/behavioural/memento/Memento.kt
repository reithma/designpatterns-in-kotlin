package behavioural.mediator

data class Memento(val state: String)

class Originator(var state: String) {
    fun createMemento(): Memento = Memento(state = state)

    fun restoreMemento(memento: Memento) {
        state = memento.state
    }
}

/**
 * The Memento-pattern is used when saving and restoring a previous state is needed
 * without revealing implementation details.
 * It consists of 3 Components:
 * Memento -> Stores state
 * Originator -> Creates state
 * Caretaker -> decides to save or restore state
 */
class CareTaker {
    private val mementoList = arrayListOf<Memento>()

    fun saveState(state: Memento) {
        mementoList.add(state)
    }

    fun restore(index: Int): Memento = mementoList[index]
}
