package behavioural.mediator

class ChatUser(private val mediator: Mediator, private val name: String) {
    fun send(msg: String) {
        println("$name: Sent -> $msg")
        mediator.sendMessage(msg, this)
    }

    fun recieve(msg: String) {
        println("$name: Recieved -> $msg")
    }
}

/**
 * The mediator pattern is used when a central object, communicatingg with different objects is needed.
 * Objects only talk to the mediator, not to the other objects.
 * This is used to reduce dependencies between objects
 */
class Mediator {
    private val users = arrayListOf<ChatUser>()

    fun sendMessage(msg: String, user: ChatUser) {
        users.filter { it != user }
            .forEach { it.recieve(msg) }
    }

    fun addUser(user: ChatUser): Mediator = apply { users.add(user) }
}
