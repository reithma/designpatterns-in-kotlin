package creational.abstractfactory

class DatabaseDatasource : DataSource {
    private var myData = ""
    override fun readData(): String = myData
    override fun sendData(data: String) {
        myData = data
    }
}

class HistoryDatasource : DataSource {
    private val history = mutableListOf<String>()
    override fun sendData(data: String) {
        history += data
    }

    override fun readData(): String = history.removeLastOrNull() ?: "No such element"

    override fun ping() {
        super.ping()
        println("....PONG\n")
    }
}
