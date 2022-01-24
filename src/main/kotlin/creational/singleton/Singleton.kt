package creational.singleton

/**
 * Singleton is used when only a single instance of an object is required
 * Examples: Connectors, Communicationservices, Loggers
 *
 */
object Singleton {
    private var text: String = ""

    init {
        println("I am the One Singleton!")
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getText() = text

}
