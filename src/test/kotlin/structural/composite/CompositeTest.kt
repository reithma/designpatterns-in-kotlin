package structural.composite

import org.assertj.core.api.Assertions
import org.assertj.core.data.Offset
import org.junit.Test
import java.math.BigDecimal

class Computer : Composite(name = "PC")
class Processor : Equipment(name = "i7-9600", price = BigDecimal(750.0))
class Harddrive : Equipment(name = "WD-750 1TB", price = BigDecimal(250.0))
class Memory : Composite(name = "Memory")
class ROM : Equipment(name = "onBoardRom", price = BigDecimal(119.99))
class RAM : Equipment(name = "Intel-Extreme 64GB", price = BigDecimal(349.99))

class CompositeTest {

    @Test
    fun `composite test`() {
        val memory = Memory().add(ROM()).add(RAM())
        val pc = Computer().add(memory).add(Processor()).add(Harddrive())

        Assertions.assertThat(pc.name).isEqualTo("PC")
        Assertions.assertThat(pc.price).isCloseTo(ROM().price + RAM().price + Harddrive().price + Processor().price,
            Offset.offset(BigDecimal(1e-5)))
        pc.powerOn()
        Assertions.assertThat(pc.isTurnedOn).isTrue
        Assertions.assertThat(memory.isTurnedOn).isTrue
    }
}
