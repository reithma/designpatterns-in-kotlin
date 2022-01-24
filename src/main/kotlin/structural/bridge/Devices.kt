package structural.bridge

interface Device {
    var volume: Double
    fun getName(): String
}

class Radio : Device {
    override var volume = 0.0
    override fun getName() = "Radio $this"
}

class TV : Device {
    override var volume = 10.0
    override fun getName(): String = "TV $this"
}

interface Remote {
    fun volumeUp()
    fun volumeDown()
}

/**
 * the bridge-pattern is used to decouple not connected characteristics of a base object, similar to compositon
 * in this example, the BasicRemote is used as a bridge to devices
 */
class BasicRemote(val device: Device) : Remote {
    override fun volumeUp() {
        if (device.volume < 100.0) {
            device.volume += 0.5
        }
    }

    override fun volumeDown() {
        if (device.volume > 0.0) {
            device.volume -= 0.5
        }
    }
}
