package behavioural.memento

import behavioural.mediator.CareTaker
import behavioural.mediator.Originator
import org.junit.Test
import kotlin.test.assertEquals

class MementoTest {

    @Test
    fun `test memento`() {
        val originator = Originator(state = "initialized")
        val careTaker = CareTaker()
        careTaker.saveState(originator.createMemento())

        originator.state = "State 1"
        careTaker.saveState(originator.createMemento())


        originator.state = "State 2"
        careTaker.saveState(originator.createMemento())


        originator.state = "State 3"
        careTaker.saveState(originator.createMemento())

        println("Current state: '${originator.state}'")
        assertEquals(originator.state, "State 3")

        originator.restoreMemento(careTaker.restore(1))

        assertEquals(originator.state, "State 1")
        println("Current state: '${originator.state}'")
    }
}
