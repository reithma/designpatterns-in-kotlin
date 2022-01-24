package creational.abstractfactory

/**
 * The abstract-factory pattern is used when a layer of abstraction is needed when functionality is same,
 * but implementation is different, just like the factory pattern,
 * but also in addition abstracts the kind of object behind the abstraction
 *
 * Is a level of abstraction higher than factory pattern
 *
 */
abstract class DatasourceAbstractFactory {
    abstract fun makeDatasource(): DataSource

    companion object {
        inline fun <reified T : DataSource> create(): DatasourceAbstractFactory =
            when (T::class) {
                DatabaseDatasource::class -> DatabaseDatasourceFactory()
                HistoryDatasource::class -> HistoryDatasourceFactory()
                else -> throw IllegalArgumentException("Type not supported")
            }
    }
}

class DatabaseDatasourceFactory : DatasourceAbstractFactory() {
    override fun makeDatasource(): DataSource = DatabaseDatasource()
}

class HistoryDatasourceFactory : DatasourceAbstractFactory() {
    override fun makeDatasource(): DataSource = HistoryDatasource()
}
