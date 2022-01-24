package creational.prototype

abstract class Shape : Cloneable {
    var id: String? = null
    var type: String? = null

    abstract fun draw()

    public override fun clone(): Any {
        var clone: Any? = null
        try {
            clone = super.clone()

        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return clone!!
    }
}

class Rectangle : Shape() {
    override fun draw() {
        println("Drawing Rectangle: [=====]")
    }

    init {
        type = "Rectangle"
    }
}

class Circle : Shape() {
    override fun draw() {
        println("Drawing Circle: OOOOOOOOOOOOOOO")
    }

    init {
        type = "Circle"
    }
}

class Triangle : Shape() {
    override fun draw() {
        println("Drawing Triangle: /_\\")
    }

    init {
        type = "Triangle"
    }
}

object ShapeCache {
    private val shapeCache = hashMapOf<String, Shape>()

    fun loadCache() {
        val rectangle = Rectangle()
        val circle = Circle()
        val triangle = Triangle()

        shapeCache["1"] = rectangle
        shapeCache["2"] = circle
        shapeCache["3"] = triangle
    }

    fun getShape(shapeId: String) = shapeCache[shapeId]?.clone() as Shape
}
