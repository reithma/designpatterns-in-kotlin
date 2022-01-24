package structural.adapter

data class Country(val name: String)

/**
 * The adapter pattern converts the interface of a class into another interface that is suitable for a client
 * The adapter may also convert data from one format to another
 */
class MyBeautifulCountryAdapter {
    private val thirdPartyClass = ThirdPartyClass()

    fun getById(id: String) = thirdPartyClass.produceByUniqueIdentifyer(id) ?: ""

    fun addToList(countryName: String) =
        thirdPartyClass.persistByCallingTitle(countryName, alwaysStrangeBiggycase = false)

    fun returnCollection(): Iterable<Country> = thirdPartyClass
        .stringstLisththth
        .filterNotNull()
        .map { Country(name = it) }
}
