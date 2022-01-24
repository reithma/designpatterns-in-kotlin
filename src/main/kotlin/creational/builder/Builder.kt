package creational.builder

/**
 * The builder pattern is used when a class has many possibilities of configuring different constructor-attributes
 * for a class. kotlin has named parameters which are not usable from java though.
 * We can use the builder pattern as workaround to achieve the same functionality
 */
class Component private constructor(builder: Builder) {
    var title: String? = null
    var param1: Boolean = false
    var param2: Int? = null
    var param3: Double = 12.2

    class Builder {
        var title: String? = null
            private set
        private var param1: Boolean = false
        private var param2: Int? = null
        private var param3: Double = 12.2
        fun setTitle(title: String) = apply { this.title = title }
        fun setParam1(param: Boolean) = apply { this.param1 = param }
        fun getParam1() = this.param1
        fun setParam2(param: Int) = apply { this.param2 = param }
        fun getParam2() = this.param2
        fun setParam3(param: Double) = apply { this.param3 = param }
        fun getParam3() = this.param3

        fun build() = Component(this)
    }

    init {
        title = builder.title
        param1 = builder.getParam1()
        param2 = builder.getParam2()
        param3 = builder.getParam3()
    }
}
