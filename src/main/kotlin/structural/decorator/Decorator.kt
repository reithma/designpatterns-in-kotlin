package structural.decorator

interface CoffeeMachine {
    fun makeCoffee()
    fun refillWatertank()
}

class NormalCoffeeMachine : CoffeeMachine {
    var watertank = 1000.0

    override fun makeCoffee() {
        println("Normal CoffeeMachine brewing Coffee")
    }

    override fun refillWatertank() {
        watertank = 1000.0
    }
}

/**
 * The decorator-pattern is used when an existing object should be extended with additional behaviour,
 * without altering existing code
 */
class EnhancedCoffeemachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {
    // overriding behaviour
    override fun makeCoffee() {
        println("EnhancedCoffeemachine brewing enhanced Coffee")
    }

    // extending behaviour
    fun makeMilkCoffee(){
        println("EnhancedCoffeemachine brewing milk coffee")
        coffeeMachine.makeCoffee()
        println("adding some milk")
    }
}
