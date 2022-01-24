package structural.decorator

import org.junit.Test

class DecoratorTest {
    @Test
    fun `test coffeemachines`() {
        val normalCoffeeMachine = NormalCoffeeMachine()
        val enhancedCoffeemachine = EnhancedCoffeemachine(normalCoffeeMachine)
        normalCoffeeMachine.makeCoffee()
        println("-----------------------------------\n\n")
        enhancedCoffeemachine.makeCoffee()
        println("-----------------------------------\n\n")
        enhancedCoffeemachine.makeMilkCoffee()
    }
}
