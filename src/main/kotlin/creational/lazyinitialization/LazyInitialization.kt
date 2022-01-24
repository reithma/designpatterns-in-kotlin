package creational.lazyinitialization

data class MyIntenseComponent(var msg: String = "") {
    private val msgSnippet
        get() = if (msg != "") " on $msg" else ""

    fun doSomething() = println("working$msgSnippet...")

    init {
        println("INIT [$msg]...")
    }
}

/**
 * Useful for performance management. Object only gets created when it is actually needed,
 * so preload times may be smaller than usual
 */
class LazyInitialization {
    val comp by lazy { MyIntenseComponent("auto") }

    lateinit var manualComponent: MyIntenseComponent

    fun doTheThing() {
        comp.doSomething()
    }

    fun doTheOtherThing() {
        manualComponent = MyIntenseComponent("manual")
        manualComponent.doSomething()
    }
}
