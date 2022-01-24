package behavioural.mediator

import org.junit.Test

class MediatorTest {
    @Test
    fun `mediator test`() {
        val mediator = Mediator()
        val alice = ChatUser(mediator, "Alice")
        val bob = ChatUser(mediator, "Bob")
        val mary = ChatUser(mediator, "Mary")

        mediator.addUser(alice).addUser(bob).addUser(mary)

        mary.send("Hi Chat")
    }
}
