package creational.abstractfactory

interface DataSource {
    fun sendData(data: String)

    fun readData(): String

    fun ping() = println("Ping...!")
}
