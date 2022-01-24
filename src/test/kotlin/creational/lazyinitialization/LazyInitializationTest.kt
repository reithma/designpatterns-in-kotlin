package creational.lazyinitialization

import org.assertj.core.api.Assertions
import org.junit.Test
import kotlin.test.assertFailsWith

class LazyInitializationTest {

    @Test
    fun `test init on first call in assert`() {
        val lazyInit = LazyInitialization()
        Assertions.assertThat(lazyInit.comp).isNotNull
    }

    @Test
    fun `testing lazyinit`() {
        println("pre-instantiation")
        val lazyInit = LazyInitialization()
        println("successfully instantiated class, now asserting")
        Assertions.assertThat(lazyInit.comp).isNotNull
        assertFailsWith<UninitializedPropertyAccessException> { lazyInit.manualComponent }
        println("\n\nDoing the thing")
        lazyInit.doTheThing()
        Assertions.assertThat(lazyInit.comp).isNotNull
        assertFailsWith<UninitializedPropertyAccessException> { lazyInit.manualComponent }
        println("\n\nDoing the other thing")
        lazyInit.doTheOtherThing()
        Assertions.assertThat(lazyInit.comp).isNotNull
        Assertions.assertThat(lazyInit.manualComponent).isNotNull
    }
}
