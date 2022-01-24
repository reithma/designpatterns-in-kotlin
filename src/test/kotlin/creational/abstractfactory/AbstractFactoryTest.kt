package creational.abstractfactory

import org.assertj.core.api.Assertions
import org.junit.Test

class AbstractFactoryTest {

    @Test
    fun `test history factory`() {
        val historyDatasource = DatasourceAbstractFactory.create<HistoryDatasource>().makeDatasource()

        Assertions.assertThat(historyDatasource.readData()).isEqualTo("No such element")
        historyDatasource.sendData("Test")
        Assertions.assertThat(historyDatasource.readData()).isEqualTo("Test")
        Assertions.assertThat(historyDatasource.readData()).isEqualTo("No such element")
    }

    @Test
    fun `test datasource factory`() {
        val datasourceFactory = DatasourceAbstractFactory.create<DatabaseDatasource>().makeDatasource()
        datasourceFactory.ping()
        Assertions.assertThat(datasourceFactory.readData()).isEmpty()
        datasourceFactory.sendData("Test")
        Assertions.assertThat(datasourceFactory.readData()).isEqualTo("Test")
        Assertions.assertThat(datasourceFactory.readData()).isEqualTo("Test")
    }

    @Test
    fun `test both factories`() {
        val datasourceFactory = DatasourceAbstractFactory.create<DatabaseDatasource>().makeDatasource()
        val historyDatasource = DatasourceAbstractFactory.create<HistoryDatasource>().makeDatasource()

        historyDatasource.sendData("Test")
        datasourceFactory.sendData("Test")
        Assertions.assertThat(datasourceFactory.readData()).isEqualTo(historyDatasource.readData())
    }
}
