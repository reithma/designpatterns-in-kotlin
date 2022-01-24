package behavioural.state

import org.junit.Test
import kotlin.test.assertEquals

class StateTest {

    @Test
    fun `first state test`() {
        val authProvider = AuthorizationProvider()
        assertEquals(authProvider.isAuthorized, false)
        println(authProvider)
        authProvider.login("Matthias")
        println(authProvider)
        assertEquals(authProvider.isAuthorized, true)
        assertEquals(authProvider.username, "Matthias")
        authProvider.logout()
        println(authProvider)
        assertEquals(authProvider.isAuthorized, false)
        assertEquals(authProvider.username, "Unknown")
    }

}
