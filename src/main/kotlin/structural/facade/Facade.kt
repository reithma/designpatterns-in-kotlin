package structural.facade

enum class State(val ID: Int) {
    NONE(0),
    ONE(1),
    TWO(2),
    THREE(3),
}

class ComplicatedComponentImpl {
    var state: State = State.NONE
    fun doTheFirstThing(): ComplicatedComponentImpl {
        return apply { state = State.ONE }
    }

    fun doTheSecondThing(): ComplicatedComponentImpl {
        return apply { state = State.TWO }
    }

    fun doTheThirdThing(): ComplicatedComponentImpl {
        return apply { state = State.THREE }
    }

    fun getReply() = if (state != State.THREE) -1 else state.ID
}

/**
 * the facade-pattern is used to provide a simple interface to complex functionality
 */
class Facade {
    fun doTheAnswerThingy(): Int {
        val compImpl = ComplicatedComponentImpl()
        return compImpl.doTheFirstThing().doTheSecondThing().doTheThirdThing().getReply()
    }
}
