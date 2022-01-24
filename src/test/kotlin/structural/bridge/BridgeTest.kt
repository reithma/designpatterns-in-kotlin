package structural.bridge

import org.assertj.core.api.Assertions
import org.assertj.core.data.Offset
import org.junit.Test

class BridgeTest {
    @Test
    fun `test tv`() {
        val tv = TV()
        Assertions.assertThat(tv.volume).isCloseTo(10.0, Offset.offset(1e-5))
    }

    @Test
    fun `test radio`() {
        val tv = Radio()
        Assertions.assertThat(tv.volume).isCloseTo(0.0, Offset.offset(1e-5))
    }

    @Test
    fun `test basic tv-remote function`() {
        val tv = TV()
        val tvRemote = BasicRemote(device = tv)

        Assertions.assertThat(tv.volume).isCloseTo(10.0, Offset.offset(1e-5))
        repeat(3) { tvRemote.volumeUp() }
        Assertions.assertThat(tv.volume).isCloseTo(11.5, Offset.offset(1e-5))
        repeat(2) { tvRemote.volumeDown() }
        Assertions.assertThat(tv.volume).isCloseTo(10.5, Offset.offset(1e-5))
    }

    @Test
    fun `test basic radio-remote function`() {
        val radio = Radio()
        val radioRemote = BasicRemote(device = radio)

        Assertions.assertThat(radio.volume).isCloseTo(0.0, Offset.offset(1e-5))
        repeat(3) { radioRemote.volumeUp() }
        Assertions.assertThat(radio.volume).isCloseTo(1.5, Offset.offset(1e-5))
        repeat(2) { radioRemote.volumeDown() }
        Assertions.assertThat(radio.volume).isCloseTo(0.5, Offset.offset(1e-5))
    }

    @Test
    fun `test max and min volumes`() {
        val tv = TV()
        val tvRemote = BasicRemote(device = tv)
        repeat(500) { tvRemote.volumeUp() }
        Assertions.assertThat(tv.volume).isCloseTo(100.0, Offset.offset(1e-5))
        repeat(800) { tvRemote.volumeDown() }
        Assertions.assertThat(tv.volume).isCloseTo(0.0, Offset.offset(1e-5))

        val radio = Radio()
        val radioRemote = BasicRemote(device = radio)
        repeat(500) { radioRemote.volumeUp() }
        Assertions.assertThat(radio.volume).isCloseTo(100.0, Offset.offset(1e-5))
        repeat(800) { radioRemote.volumeDown() }
        Assertions.assertThat(radio.volume).isCloseTo(0.0, Offset.offset(1e-5))
    }

}
