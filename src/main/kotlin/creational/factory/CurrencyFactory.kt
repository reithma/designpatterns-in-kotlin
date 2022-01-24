package creational.factory

import java.math.BigInteger

sealed class Country(private val language: String) {
    fun getLang() = language
}

data class Austria(
    val currencyName: String,
) : Country("österreichisch") {
    val currencyImpl: Currency = CurrencyFactory.currencyForCountry(this)
}

data class Spain(
    val currencyName: String,
) : Country("espanol") {
    var currencyImpl: Currency = CurrencyFactory.currencyForCountry(this)
}

data class Usa(
    val currencyName: String,
    val numberOfGuns: BigInteger,
) : Country("FREEDOM")

data class GreatBritain(
    val currencyName: String,
    val numberOfTeabags: BigInteger,
) : Country("english")


class Currency(val code: String)

/**
 * The factory-pattern is used when a layer of abstraction is needed when functionality is same,
 * but implementation is different.
 * "Provides a way to access functionality without caring about implementation"
 */
object CurrencyFactory {
    fun currencyForCountry(country: Country): Currency =
        when (country) {
            is Austria -> {
                println("Österreich ist frei!!!")
                Currency("EUR")
            }
            is Spain -> {
                println("viva la austria!")
                Currency("EUR")
            }
            is Usa -> {
                print("I CANNOT HEAR YOU OVER THE SOUND OF MY FREEDOM")
                Currency("USD")
            }
            is GreatBritain -> {
                println("I'm a legal alien!")
                Currency("GBP")
            }
        }
}
