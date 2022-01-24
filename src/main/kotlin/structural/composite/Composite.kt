package structural.composite

import java.math.BigDecimal

open class Equipment(
    open val price: BigDecimal,
    open var isTurnedOn: Boolean = false,
    val name: String,
) {
    open fun powerOn() {
        isTurnedOn = true
        println("$name is turned on")
    }

    open fun powerOff() {
        isTurnedOn = false
        println("$name is turned off")
    }
}

/**
 * The composite-pattern is used to combine different (existing) components in another object.
 * While doing so, the components are automatically ordered in a tree structure.
 * The gain is that the programmer is able to manipulate many objects as a single one
 */
open class Composite(name: String) : Equipment(price = BigDecimal.ZERO, name = name) {
    private val equipments = ArrayList<Equipment>()
    override var isTurnedOn: Boolean = false

    override val price: BigDecimal
        get() = equipments.sumOf { it.price }

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }

    override fun powerOn() {
        equipments.forEach {
            it.powerOn()
        }
        super.powerOn()
    }

    override fun powerOff() {
        equipments.forEach {
            it.powerOff()
        }
        super.powerOff()
    }
}
