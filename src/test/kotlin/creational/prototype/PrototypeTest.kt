package creational.prototype

import org.assertj.core.api.Assertions
import org.junit.Test

class PrototypeTest {
    @Test
    fun `test prototype cache`() {
        ShapeCache.loadCache()
        val clonedShape1 = ShapeCache.getShape("1")
        Assertions.assertThat(clonedShape1.type).isEqualTo("Rectangle")
        clonedShape1.draw()
        val clonedShape2 = ShapeCache.getShape("2")
        Assertions.assertThat(clonedShape2.type).isEqualTo("Circle")
        clonedShape2.draw()
        val clonedShape3 = ShapeCache.getShape("3")
        Assertions.assertThat(clonedShape3.type).isEqualTo("Triangle")
        clonedShape3.draw()
        val clonedShape4 = ShapeCache.getShape("3")
        Assertions.assertThat(clonedShape3.type).isEqualTo(clonedShape4.type)
        clonedShape4.type = "wabbawabba"
        Assertions.assertThat(clonedShape3.type).isNotEqualTo(clonedShape4.type)
    }
}
