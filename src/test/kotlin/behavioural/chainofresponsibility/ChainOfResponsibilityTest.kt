package behavioural.chainofresponsibility

import org.assertj.core.api.Assertions
import org.junit.Test

class ChainOfResponsibilityTest {

    @Test
    fun `test body payload-item`() {
        val bodyPayloadHeader = BodyPayloadHeader(body = "Body: {\"userame\":\"Matthias\"}")


        val messageWithAuthentication = bodyPayloadHeader.addHeader("Headers with body")
        Assertions.assertThat(messageWithAuthentication).isEqualTo(
            """
                Headers with body
                Body: {"userame":"Matthias"}
            """.trimIndent()
        )
    }

    @Test
    fun `test contenttype chain`() {
        val contentTypeHeader = ContentTypeHeader(contentType = "application/json")
        val bodyPayloadHeader = BodyPayloadHeader(body = "Body: {\"userame\":\"Matthias\"}")

        contentTypeHeader.next = bodyPayloadHeader

        val messageWithAuthentication = contentTypeHeader.addHeader("Headers without authentication")
        Assertions.assertThat(messageWithAuthentication).isEqualTo(
            """
                Headers without authentication
                ContentType: application/json
                Body: {"userame":"Matthias"}
            """.trimIndent()
        )
    }

    @Test
    fun `test full chain`() {
        val authenticationHeader = AuthenticationHeader(token = "1AF67DD_CAFE")
        val contentTypeHeader = ContentTypeHeader(contentType = "application/json")
        val bodyPayloadHeader = BodyPayloadHeader(body = "Body: {\"userame\":\"Matthias\"}")

        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayloadHeader

        val messageWithAuthentication = authenticationHeader.addHeader("Headers with authentication")
        Assertions.assertThat(messageWithAuthentication).isEqualTo(
            """
                Headers with authentication
                Authorization: 1AF67DD_CAFE
                ContentType: application/json
                Body: {"userame":"Matthias"}
            """.trimIndent()
        )
    }
}
