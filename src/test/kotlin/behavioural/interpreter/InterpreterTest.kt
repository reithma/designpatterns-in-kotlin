package behavioural.interpreter

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InterpreterTest {
    @Test
    fun `test basic interpreter functionality`() {
        val expressionOne = StringComparer("exprOne")
        val expressionTwo = StringComparer("exprTwo")

        assertTrue(expressionOne.compare("exprOne"))
        assertTrue(expressionTwo.compare("exprTwo"))
        assertFalse(expressionOne.compare("exprone"))
        assertFalse(expressionTwo.compare("exprtwo"))

        assertTrue(expressionOne.compare("thisIsexprOneTwoThree"))
        assertFalse(expressionOne.compare("expr"))
    }

    @Test
    fun `test or expression`() {
        val expressionOne = StringComparer("exprOne")
        val expressionTwo = StringComparer("exprTwo")
        val orComparer = OrComparer(expressionOne, expressionTwo)

        assertTrue(orComparer.compare("exprOne"))
        assertTrue(orComparer.compare("exprTwo"))
        assertFalse(orComparer.compare("somethingElse"))

        assertFalse(orComparer.compare("expr"))
        assertTrue(orComparer.compare("exprOneTwoThree"))
    }


    @Test
    fun `test and expression`() {
        val expressionOne = StringComparer("exprOne")
        val expressionTwo = StringComparer("exprTwo")
        val andComparer = AndComparer(expressionOne, expressionTwo)

        assertTrue(andComparer.compare("exprOneexprTwo"))
        assertTrue(andComparer.compare("exprTwoexprOne"))
        assertTrue(andComparer.compare("exprTwo, exprOne"))
        assertTrue(andComparer.compare("exprOne, exprTwo"))
    }

}
