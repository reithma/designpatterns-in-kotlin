package creational.singleton

import org.assertj.core.api.Assertions
import org.junit.Test

class SingletonTest {
    @Test
    fun `create singleton`() {
        val singleton = Singleton
        singleton.setText("I am the One!!!")
        Assertions.assertThat(singleton.getText()).isEqualTo("I am the One!!!")
    }

    @Test
    fun `assignments reference same object`() {
        val singleOne = Singleton
        val singleTwon = Singleton

        Assertions.assertThat(singleOne).isSameAs(singleTwon)
        Assertions.assertThat(singleOne::getText).equals(singleTwon::getText)

    }

    @Test
    fun `test if both reference same object`() {
        val singleOne = Singleton
        val singleTwon = Singleton

        singleOne.setText("Text0r Chang0rt")
        Assertions.assertThat(singleOne::getText).equals(singleTwon::getText)
    }
}
