package structural.proxy

import org.junit.Test

class ProxyTest {

    @Test
    fun `base proxy test`() {
        val image = ProxyImage(filename = "test.jpg")

        // load image from disk
        image.display()
        println("-----------------")

        // second invocation does not load the file anymore
        image.display()
    }
}
