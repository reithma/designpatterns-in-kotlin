package structural.facade

import org.assertj.core.api.Assertions
import org.junit.Test

class FacadeTest {

    @Test
    fun `compare facade with original functionality`() {
        val facade = Facade()
        val complicatedComponentImpl = ComplicatedComponentImpl()

        val facReply = facade.doTheAnswerThingy()
        complicatedComponentImpl.doTheFirstThing()
        complicatedComponentImpl.doTheSecondThing()
        complicatedComponentImpl.doTheThirdThing()
        val compReply = complicatedComponentImpl.getReply()
        Assertions.assertThat(facReply).isEqualTo(compReply)
    }
}
