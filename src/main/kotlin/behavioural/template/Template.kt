package behavioural.template

/**
 * The template pattern is used when an order of an algorithm has to be maintained, but has some steps that may be
 * changed by the user of the child class
 */
abstract class BaseTemplate {
    var printString: String = "BaseTemplate"

    fun run(): String {
        println("Calling run from BaseTemplate")
        overrideFuncOne()
        mandatoryStep()
        overrideFuncTwo()
        println("Done\n")

        return printString
    }

    protected abstract fun overrideFuncOne()

    private fun mandatoryStep() {
        println("Calling base Template function mandatoryStep with value $printString")
    }

    protected abstract fun overrideFuncTwo()

}

class TemplateImplOne(private val strVal: String = "I am the standard Text") : BaseTemplate() {
    override fun overrideFuncOne() {
        println("Setting printString to \"$strVal\"")
        printString = strVal
    }

    override fun overrideFuncTwo() {
        println("Successfully executed second overridden function")
    }

}

class TemplateImplTwo : BaseTemplate() {
    override fun overrideFuncOne() {}

    override fun overrideFuncTwo() {
        println("Did the second thing!")
    }

}
