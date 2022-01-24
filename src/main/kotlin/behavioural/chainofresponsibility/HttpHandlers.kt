package behavioural.chainofresponsibility

/**
 * The chain of responsibility pattern defines a chain of handlers for specific requests.
 * Each handler contains a reference to the next handler.
 * If a hanlder is responsible for a request, it will handle it and/or passes it on, depending on the request
 * The structure may be either a CHAIN ("list") or a TREE of responsibility
 */
interface HttpHandlerChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(private val token: String?, var next: HttpHandlerChain? = null) : HttpHandlerChain {
    override fun addHeader(inputHeader: String) = "$inputHeader\nAuthorization: $token".run {
        next?.addHeader(this) ?: this
    }
}

class ContentTypeHeader(private val contentType: String, var next: HttpHandlerChain? = null) : HttpHandlerChain {
    override fun addHeader(inputHeader: String) = "$inputHeader\nContentType: $contentType".run {
        next?.addHeader(this) ?: this
    }
}

class BodyPayloadHeader(private val body: String, var next: HttpHandlerChain? = null) : HttpHandlerChain {
    override fun addHeader(inputHeader: String) = "$inputHeader\n$body".run {
        next?.addHeader(this) ?: this
    }
}
