package structural.proxy

interface Image {
    fun display()
}

class RealImage(private val filename: String) : Image {
    init {
        loadFromDisk(filename = filename)
    }

    override fun display() {
        println("RealImage: Displaying $filename")
    }

    private fun loadFromDisk(filename: String) {
        println("RealImage: Loading $filename")
    }
}

/**
 * the proxy pattern is used when functionality has to be added either before and/or after a call to an object
 * it's similar to facade, except that the proxy has the same interface as the proxied class
 * it's similar to decorator, except the proxy manages the lifecycle of it's object instead of the client
 */
class ProxyImage(private val filename: String) : Image {
    private var realImage: RealImage? = null

    override fun display() {
        println("ProxyImage: Displaying $filename")
        if (realImage == null) {
            realImage = RealImage(filename = filename)
        }
        realImage!!.display()
    }
}
