package creational.factory

import org.assertj.core.api.Assertions
import org.junit.Test

class FactoryTest {

    @Test
    fun `test austrian currency`() {
        val austria = Austria("Schilling")

        Assertions.assertThat("Schilling").isEqualTo(austria.currencyName)
        Assertions.assertThat("EUR").isEqualTo(austria.currencyImpl.code)
    }

    @Test
    fun `test different countries`() {
        val austria = Austria("Schilling")
        val spain = Spain("Pesos")

        val manualAustrianFactory = CurrencyFactory.currencyForCountry(austria)

        Assertions.assertThat("Schilling").isEqualTo(austria.currencyName)
        Assertions.assertThat(austria.currencyImpl.code).isEqualTo(manualAustrianFactory.code)
        Assertions.assertThat("EUR").isEqualTo(austria.currencyImpl.code)

        Assertions.assertThat(austria.currencyImpl).isNotEqualTo(spain.currencyImpl)
    }
}


