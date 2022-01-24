package creational.builder

import org.assertj.core.api.Assertions
import org.assertj.core.data.Offset
import org.junit.Test


class BuilderTest {

    @Test
    fun `test builder pattern`() {
        val component = Component.Builder()
            .setParam3(42.0)
            .setParam2(69)
            .setTitle("MyAwesomeTitle")
            .build()
        Assertions.assertThat(component.param1).isEqualTo(false)
        Assertions.assertThat(component.param2).isEqualTo(69)
        Assertions.assertThat(component.param3).isEqualTo(42.0)
    }


    @Test
    fun `test minimal builder`() {
        val component = Component.Builder()
            .setTitle("MiniBuild")
            .build()
        Assertions.assertThat("MiniBuild").isEqualTo(component.title)
        Assertions.assertThat(false).isEqualTo(component.param1)
        Assertions.assertThat(component.param2).isNull()
        Assertions.assertThat(component.param3).isCloseTo(12.2, Offset.offset(1e-5))
    }

}
