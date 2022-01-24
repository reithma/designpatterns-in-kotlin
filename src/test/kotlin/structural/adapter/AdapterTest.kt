package structural.adapter

import org.assertj.core.api.Assertions
import org.junit.Test

class AdapterTest {

    @Test
    fun `compare third party values to adapter`() {
        val thirdPartyClass = ThirdPartyClass()
        val adapter = MyBeautifulCountryAdapter()

        thirdPartyClass.persistByCallingTitle("Test", true)
        adapter.addToList("Test")
        Assertions.assertThat(thirdPartyClass.stringstLisththth.firstOrNull())
            .isEqualTo(adapter.returnCollection().firstOrNull()?.name)
        Assertions.assertThat(thirdPartyClass.produceByUniqueIdentifyer("Test")).isEqualTo(adapter.getById("Test"))
        Assertions.assertThat(thirdPartyClass.produceByUniqueIdentifyer("x")).isNotEqualTo(adapter.getById("x"))
    }
}
