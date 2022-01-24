package behavioural.state

sealed class AuthorizationState

class Unauthorized : AuthorizationState()

class Authorized(val username: String) : AuthorizationState()

/**
 * The State-pattern is used when an object should act differently based on its internal state.
 * At any moment, there has to be a finite number of states an object can be in
 * State may be encapsulated within an object
 */
class AuthorizationProvider {
    private var state: AuthorizationState = Unauthorized()

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val username: String
        get() {
            return when (val state = this.state) {
                is Authorized -> state.username
                is Unauthorized -> "Unknown"
            }
        }

    fun login(username: String) {
        state = Authorized(username = username)
    }

    fun logout() {
        state = Unauthorized()
    }

    override fun toString() = "User $username is logged " + if(isAuthorized) "in" else "out"
}
