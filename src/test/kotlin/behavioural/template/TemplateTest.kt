package behavioural.template


import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TemplateTest {

    @Test
    fun `test if template method returns correct default value string`() {
        val concreteTemplateImplOne = TemplateImplOne()
        val result = concreteTemplateImplOne.run()
        assertEquals(concreteTemplateImplOne.printString, result)
    }

    @Test
    fun `test if template method returns correct object value`() {
        val overrideText = "I am the Override!"
        val concreteTemplateImplOne = TemplateImplOne(overrideText)
        assertEquals("BaseTemplate", concreteTemplateImplOne.printString)
        assertEquals(overrideText, concreteTemplateImplOne.run())
    }

    @Test
    fun `printString differs from run result because at time of invocation printString is still default value`() {
        val concreteTemplateImplOne = TemplateImplOne()
        assertNotEquals(concreteTemplateImplOne.printString, concreteTemplateImplOne.run())
    }


}
