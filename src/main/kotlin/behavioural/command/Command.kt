package behavioural.command

interface Command {
    fun execute()
    fun undo()
}

data class Item(val itemId: Long, val price: Double)
data class Order(val orderId: Long, var isPayed: Boolean = false, val items: HashMap<Long, Int> = hashMapOf())


/**
 * The Command-pattern is used when a request is wrapped in an object that contains all relevant info
 * This is often used when a command-history needs to be implemented where commands are to be undone and redone
 */
class OrderAddCommand(val order: Order, val itemId: Long, val amount: Int) : Command {
    override fun execute() {
        println("Adding item(s) with ID [$itemId] to cart -> Amount: $amount")
        val currentAmount = order.items.getOrDefault(itemId, 0)
        order.items[itemId] = currentAmount + amount
    }

    override fun undo() {
        println("Removing item(s) with ID [$itemId] to cart -> Amount: $amount")
        val currentAmount = order.items[itemId] ?: 0
        if (currentAmount - amount <= 0) {
            order.items.remove(itemId)
        } else {
            order.items[itemId] = currentAmount - amount
        }
    }
}

class OrderPayCommand(val order: Order) : Command {
    override fun execute() {
        println("Paying for order with ID [${order.orderId}]")
        order.isPayed = true
    }

    override fun undo() {
        println("Revoke payment for order with ID [${order.orderId}]")
        order.isPayed = false
    }
}

class OrderCommandProcessor {
    private val commandQueue = mutableListOf<Command>()
    private val commandHistory = mutableListOf<Command>()

    fun addToQueue(command: Command): OrderCommandProcessor = apply { commandQueue.add(command) }

    fun processCommands(): OrderCommandProcessor = apply {
        commandQueue.forEach {
            it.execute()
            commandHistory.add(it)
        }
        commandQueue.clear()
    }.also { println("Queue successfully executed\n\n") }

    fun revertCommand(steps: Int) = apply {
        val commandSteps = commandHistory.reversed().take(steps)
        commandSteps.forEach {
            it.undo()
        }
        commandHistory.removeAll(commandSteps)
    }.also { println("Step(s) successfully reverted\n\n") }
}
